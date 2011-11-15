/**	Klasse welche die CPU des Mini-Power-PC Projektes abbildet.
 * 	Die CPU soll eine 16 Bit CPU simulieren welche folgende
 * 	Spezifikationen besitzt:
 * 	- Befehlsregister (16 Bit)
 * 	- Befehlszähler
 * 	- Akkumulator (Adresse: 00 / 16 Bit)
 * 	- Register 1  (Adresse: 01 / 16 Bit)
 * 	- Register 2  (Adresse: 10 / 16 Bit)
 * 	- Register 3  (Adresse: 11 / 16 Bit)
 * 	- Carry Bit
 * 	- Hauptspeicher (1 KiB; jede Speicheradresse kann 8 Bit speichern;
 * 	  		ab Adresse 100 Programmspeicher; ab Adresse 500 Datenspeicher) 
 * 	- Counter (wird im Emulator verwendet um die Anzahl der ausgeführten
 * 	  		Rechenschritte zu zählen)
 * 
 */
package info.mini.power.pc;

import java.io.FileNotFoundException;

/**
 * @author Mathias Weigert & Miro Ljubicic
 *
 */
public class CentralProcessingUnit {

	Register breg = new Register();
	Register bcount = new Register();
	Register akku = new Register();
	Register reg1 = new Register();
	Register reg2 = new Register();
	Register reg3 = new Register();
	boolean cbit = false;
	Memory ram = new Memory();
	int counter = 0;

	/**
	 * Methode welche den internen Counter um eins erhöht.
	 */
	public void increaseCounter() {
		counter++;
	}

	/**
	 * Methode welche alle Werte auf Start stellt und die Register komplett leert.
	 */
	public void initSystem() {
		breg.flushRegister();
		bcount.setRegisterValue("0000000001100100");
		akku.flushRegister();
		reg1.flushRegister();
		reg2.flushRegister();
		reg3.flushRegister();
		cbit = false;
		ram.flushMemory();
		counter = 0;
	}

	/**
	 * Methode welche den Wert des Carry Bits setzt.
	 * @param bol Boolean Wert auf welchen das Carry Bit gesetzt werden soll. 
	 */
	public void setCarryBit(boolean bol) {
		cbit = bol;
	}

	/**
	 * Methode welche den Wert des Befehlszählers als Integer Wert zurück liefert.
	 * @return Integer mit dem Wert des Befehlcounters 
	 */
	public int getBcountAsInt() {
		return new Converter().binToDez(bcount.getRegisterValue());
	}

	/**
	 * Methode welche den Wert des Befehlszählers als String (Binärzahl) zurück liefert.
	 * @return String welcher den Binär Wert des Befehlcounters enthält.
	 */
	public String getBcountAsString() {
		return bcount.getRegisterValue();
	}

	/**
	 * Methode welche den internen Counter zurückgibt.
	 * @return Integer welche den Wert des internen Counters beinhaltet.
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Methode welche den Benutzer ein Assembler Program auswählen lässt und dieses in den 
	 * Programspeicher lädt.
	 * @throws FileNotFoundException
	 */
	public void loadProgramToMemory() throws FileNotFoundException {
		GUI_OpenFileDialog fd = new GUI_OpenFileDialog();
		Loader loader = new Loader();
		Memory progMemory = new Memory();
		progMemory = loader.loadFileInMemory(fd.getChoosedFile());
		for (int i = 100; i < 500; i++) {
			String binAdr = new Binary(16, i, false).getBinaryValueAsStringIntern();
			ram.setMemoryValue(binAdr, progMemory.getMemAdressValue(binAdr));
		}
	}
	
	/**
	 * Methode welche den UserInput aufruft und die Zahlen in den Hauptspeicher schreibt.
	 */
	public void waitForUserInput() {
		UserInput uinput = new UserInput();
		ram = uinput.userInput(ram);
	}

	/**
	 * Methode welche den Befehl auf welchen der Befehlscounter verweist in das Befehlsregister 
	 * schreibt und diesen anschliessend ausführt.
	 * Am Ende der Methode wird der Befehlscounter um 2 erhöht (sofern kein Sprung durchgeführt wurde).
	 */
	public void executeNextCommand() {
		boolean jump = false;
		String sub = "";
		String shift = "";
		String summe ="";
		int adr = 0;
		breg.setRegisterValue(ram.getMemAdressValueWord(bcount.getRegisterValue()));
		int cmd = Integer.valueOf(new Converter().mcToMnemonic(breg.getRegisterValue()).substring(0, 3));
		switch (cmd) {
		case 0:
			sub = breg.getRegisterValue().substring(4, 6);
			cbit = false;
			if (sub.equals("00")) akku.flushRegister();
			if (sub.equals("01")) reg1.flushRegister();
			if (sub.equals("10")) reg2.flushRegister();
			if (sub.equals("11")) reg3.flushRegister();
			break;
		case 1:
			sub = breg.getRegisterValue().substring(4, 6);
			if (sub.equals("01")) summe = binAddition(akku.getRegisterValue(), reg1.getRegisterValue());
			if (sub.equals("10")) summe = binAddition(akku.getRegisterValue(), reg2.getRegisterValue());
			if (sub.equals("11")) summe = binAddition(akku.getRegisterValue(), reg3.getRegisterValue());
			akku.setRegisterValue(summe);
			break;
		case 2:
			if (breg.getRegisterValue().charAt(1) == '0') sub = "0" + breg.getRegisterValue().substring(1);
			else sub = "1" + breg.getRegisterValue().substring(1);
			akku.setRegisterValue(binAddition(akku.getRegisterValue(), sub));
			break;
		case 3:
			akku.setRegisterValue(binAddition(akku.getRegisterValue(), "0000000000000001"));
			break;
		case 4:
			akku.setRegisterValue(binAddition(akku.getRegisterValue(), "1111111111111111"));
			break;
		case 5:
			sub = breg.getRegisterValue().substring(4, 6);
			adr = new Converter().binToDez(breg.getRegisterValue().substring(4));
			if (sub.equals("00")) {
				akku.setRegisterValue(ram.getMemAdressValueWord(new Binary(16, adr, false).getBinaryValueAsStringIntern()));
			}
			if (sub.equals("01")) {
				reg1.setRegisterValue(ram.getMemAdressValueWord(new Binary(16, adr, false).getBinaryValueAsStringIntern()));
			}
			if (sub.equals("10")) {
				reg2.setRegisterValue(ram.getMemAdressValueWord(new Binary(16, adr, false).getBinaryValueAsStringIntern()));
			}
			if (sub.equals("11")) {
				reg3.setRegisterValue(ram.getMemAdressValueWord(new Binary(16, adr, false).getBinaryValueAsStringIntern()));
			}
			break;
		case 6:
			sub = breg.getRegisterValue().substring(4, 6);
			adr = new Converter().binToDez(breg.getRegisterValue().substring(4));
			if (sub.equals("00")) {
				ram.setMemoryValue(new Binary(16, adr, false).getBinaryValueAsStringIntern(),
						akku.getRegisterValue().substring(0, 8));
				ram.setMemoryValue(new Binary(16, adr + 1, false).getBinaryValueAsStringIntern(),
						akku.getRegisterValue().substring(8));
			}
			if (sub.equals("01")) {
				ram.setMemoryValue(new Binary(16, adr, false).getBinaryValueAsStringIntern(),
						reg1.getRegisterValue().substring(0, 8));
				ram.setMemoryValue(new Binary(16, adr + 1, false).getBinaryValueAsStringIntern(),
						reg1.getRegisterValue().substring(8));
			}
			if (sub.equals("10")) {
				ram.setMemoryValue(new Binary(16, adr, false).getBinaryValueAsStringIntern(),
						reg2.getRegisterValue().substring(0, 8));
				ram.setMemoryValue(new Binary(16, adr + 1, false).getBinaryValueAsStringIntern(),
						reg2.getRegisterValue().substring(8));
			}
			if (sub.equals("11")) {
				ram.setMemoryValue(new Binary(16, adr, false).getBinaryValueAsStringIntern(),
						reg3.getRegisterValue().substring(0, 8));
				ram.setMemoryValue(new Binary(16, adr + 1, false).getBinaryValueAsStringIntern(),
						reg3.getRegisterValue().substring(8));
			}
			break;
		case 7:
			
			break;
		case 8:
			break;
		case 9:
			shift = akku.getRegisterValue();
			if (shift.charAt(shift.length() - 1) == '1') cbit = true;
			else cbit = false;
			shift = "0" + shift.substring(0, shift.length() - 1);
			akku.setRegisterValue(shift);
			break;
		case 10:
			shift = akku.getRegisterValue();
			if (shift.charAt(0) == '1') cbit = true;
			else cbit = false;
			shift = shift.substring(1) + "0";
			akku.setRegisterValue(shift);
			break;
		case 11:
			break;
		case 12:
			break;
		case 13:
			String inv = "";
			for (int i = 0; i <= akku.getRegisterValue().length(); i++) {
				if (akku.getRegisterValue().charAt(i) == '0') inv += "1";
				else inv += "0";
			}
			akku.setRegisterValue(inv);
			break;
		case 14:
			if (new Converter().binToDez(akku.getRegisterValue()) == 0) {
				jump = true;
				sub = breg.getRegisterValue().substring(4, 6);
				if (sub.equals("01")) {
					bcount.setRegisterValue(reg1.getRegisterValue());
				}
				if (sub.equals("10")) {
					bcount.setRegisterValue(reg2.getRegisterValue());
				}
				if (sub.equals("11")) {
					bcount.setRegisterValue(reg3.getRegisterValue());
				}
			}
			break;
		case 15:
			if (new Converter().binToDez(akku.getRegisterValue()) != 0) {
				jump = true;
				sub = breg.getRegisterValue().substring(4, 6);
				if (sub.equals("01")) {
					bcount.setRegisterValue(reg1.getRegisterValue());
				}
				if (sub.equals("10")) {
					bcount.setRegisterValue(reg2.getRegisterValue());
				}
				if (sub.equals("11")) {
					bcount.setRegisterValue(reg3.getRegisterValue());
				}
			}
			break;
		case 16:
			if (cbit) {
				jump = true;
				sub = breg.getRegisterValue().substring(4, 6);
				if (sub.equals("01")) {
					bcount.setRegisterValue(reg1.getRegisterValue());
				}
				if (sub.equals("10")) {
					bcount.setRegisterValue(reg2.getRegisterValue());
				}
				if (sub.equals("11")) {
					bcount.setRegisterValue(reg3.getRegisterValue());
				}
			}
			break;
		case 17:
			jump = true;
				sub = breg.getRegisterValue().substring(4, 6);
				if (sub.equals("01")) {
					bcount.setRegisterValue(reg1.getRegisterValue());
				}
				if (sub.equals("10")) {
					bcount.setRegisterValue(reg2.getRegisterValue());
				}
				if (sub.equals("11")) {
					bcount.setRegisterValue(reg3.getRegisterValue());
				}
				break;
		case 18:
			//Kontrollieren kann falsch sein...
			if (new Converter().binToDez(akku.getRegisterValue()) == 0) {
				jump = true;
				adr = new Converter().binToDez(breg.getRegisterValue().substring(6));
				bcount.setRegisterValue(new Binary(16, adr,	false).getBinaryValueAsStringIntern());
			}
			break;
		case 19:
			if (new Converter().binToDez(akku.getRegisterValue()) != 0) {
				jump = true;
				adr = new Converter().binToDez(breg.getRegisterValue().substring(6));
				bcount.setRegisterValue(new Binary(16, adr,	false).getBinaryValueAsStringIntern());
			}
			break;
		case 20:
			if (cbit) {
				jump = true;
				adr = new Converter().binToDez(breg.getRegisterValue().substring(6));
				bcount.setRegisterValue(new Binary(16, adr,	false).getBinaryValueAsStringIntern());
			}
			break;
		case 21:
			jump = true;
			adr = new Converter().binToDez(breg.getRegisterValue().substring(6));
			bcount.setRegisterValue(new Binary(16, adr,	false).getBinaryValueAsStringIntern());
			break;
		case 99:
			System.out.println("Programspeicher ist leer - Programm beendet.");
			break;
		default:
			System.out.println("Fehler bei der Programm Verarbeitung.");
			break;
		}
		if (!jump) {
			adr = new Converter().binToDez(bcount.getRegisterValue());
			adr += 2;
			bcount.setRegisterValue(new Binary(16, adr, false).getBinaryValueAsStringIntern());
		}
	}
	
	/** 
	 * Methode welche einen allgemeinen Status Update des Prozessors & des Systems ausgibt. 
	 */
	public void statusOutput() {
		Converter conv = new Converter();
		System.out.println("********************************************");
		System.out.println("************ CPU Systemstatus **************");
		System.out.println("                 Step: " + counter);
		System.out.println("********************************************");
		System.out.println("Befehlsregister: " + breg.getRegisterValueExtern() + " ¦ " + conv.binToDez(breg.getRegisterValue()));
		System.out.println("    Akkumulator: " + akku.getRegisterValueExtern() + " ¦ " + conv.binToDez(akku.getRegisterValue()));
		System.out.println("     Register 1: " + reg1.getRegisterValueExtern() + " ¦ " + conv.binToDez(reg1.getRegisterValue()));
		System.out.println("     Register 2: " + reg2.getRegisterValueExtern() + " ¦ " + conv.binToDez(reg2.getRegisterValue()));
		System.out.println("     Register 3: " + reg3.getRegisterValueExtern() + " ¦ " + conv.binToDez(reg3.getRegisterValue()));
		System.out.println("********************************************");
		System.out.println("  Befehlszähler: " + bcount.getRegisterValueExtern() + " ¦ " + conv.binToDez(bcount.getRegisterValue()));
		System.out.println("      Carry Bit: " + cbit);
		System.out.println("********************************************");
		System.out.println("***************Befehlsspeicher**************");
		System.out.println("********************************************");
		String merker = getBcountAsString();
		if (conv.binToDez(merker) < 105) {
			merker = new Binary(16, 100, false).getBinaryValueAsStringIntern();
		} else {
			merker = new Binary(16, conv.binToDez(merker) - 10, false).getBinaryValueAsStringIntern();
		}
		int max = conv.binToDez(merker) + 30;
		for (int i = conv.binToDez(merker); i <= max; i += 2) {
			String out = conv.formatBinary(ram.getMemAdressValueWord(new Binary(16, i, false).getBinaryValueAsStringIntern()));
			String mnemonic = conv.mcToMnemonic(ram.getMemAdressValueWord(new Binary(16, i, false).getBinaryValueAsStringIntern()));
			System.out.println("     Memory " + i + "/" + (i+1) + ": " + out +
					" ¦ " + mnemonic);
		}
		System.out.println("********************************************");
		System.out.println("*************Memory 500 bis 529*************");
		System.out.println("********************************************");
		for (int i = 500; i < 530; i += 2) {
			String out = conv.formatBinary(ram.getMemAdressValueWord(new Binary(16, i, false).getBinaryValueAsStringIntern()));
			int value = 0;
			if (out.charAt(0) == '1') {
				value = conv.kompToInt(ram.getMemAdressValueWord(new Binary(16, i, false).getBinaryValueAsStringIntern()));
			} else value = conv.binToDez(ram.getMemAdressValueWord(new Binary(16, i, false).getBinaryValueAsStringIntern()));
			System.out.println("     Memory "+ i + "/" + (i+1) + ": " + out +
					" ¦ " +	value);
		}
	}
	
	/**
	 * Methode welche zwei Binäre Zahlen (welche als String übergeben werden) addiert
	 * und einen String mit dem binären Ergebnis zurückliefert. 
	 * @param a String mit dem ersten Summanden
	 * @param b String mit dem zweiten Summanden
	 * @return
	 */
	public String binAddition(String a, String b) {
		String summe = "";
		String invers = "";
		boolean merker = false;
		for (int i = a.length() - 1; i > 0; i--) {
			if (a.charAt(i) == b.charAt(i)) {
				if (merker) {
					if (a.charAt(i) == '0') {
						summe += "1";
						merker = false;
					} else {
						summe += "1";
						merker = true;
					}
				} else {
					if (a.charAt(i) == '0') {
						summe += "0";
					} else {
						summe += "0";
						merker = true;
					}
				}
			} else {
				if (merker) {
					summe += "0";
					merker = true;
				} else {
					summe += "1";
				}
			}
		}
		for (int i = summe.length() - 1; i > 0; i--) {
			invers += summe.charAt(i);
		}
		if (merker) cbit = true;
		else cbit = false;
		return invers;
	}
}
