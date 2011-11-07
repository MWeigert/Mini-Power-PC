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

/**
 * @author Mathias Weigert & Miro Ljubicic
 *
 */
public class CentralProcessingUnit {

	Register breg = new Register();
	String bcount = "0000000001100100";
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
		bcount = "0000000001100100";
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
		return new Converter().binToDez(bcount);
	}
	
	
	/**
	 * Methode welche den Wert des Befehlszählers als String (Binärzahl) zurück liefert.
	 * @return String welcher den Binär Wert des Befehlcounters enthält.
	 */
	public String getBcountAsString() {
		return bcount;
	}
	/**
	 * Methode welche den internen Counter zurückgibt.
	 * @return Integer welche den Wert des internen Counters beinhaltet.
	 */
	public int getCounter() {
		return counter;
	}
	
	public void waitForUserInput() {
		UserInput uinput = new UserInput();
		ram = uinput.userInput(ram);
	}
	
	/** Methode welche einen allgemeinen Status Update des Prozessors & des Systems ausgibt. 
	 * 
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
		System.out.println("  Befehlszähler: " + bcount + " ¦ " + conv.binToDez(bcount));
		System.out.println("      Carry Bit: " + cbit);
		System.out.println("********************************************");
		System.out.println("***************Befehlsspeicher**************");
		System.out.println("********************************************");
		String merker = getBcountAsString();
		if (conv.binToDez(merker) < 105) {
			merker = new Binary(16, 100).getBinaryValueAsStringIntern();
		} else {
			merker = new Binary(16, conv.binToDez(merker) - 5).getBinaryValueAsStringIntern();
		}
		int max = conv.binToDez(merker) + 15;
		for (int i = conv.binToDez(merker); i <= max; i++) {
			System.out.println("     Memory " + i + ": " +ram.getMemAdressValue(new Binary(16, i).getBinaryValueAsStringExtern()) +
					" ¦ " + conv.binToDez(ram.getMemAdressValue(new Binary(16, i).getBinaryValueAsStringIntern())));
		}
		System.out.println("********************************************");
		System.out.println("*************Memory 500 bis 529************");
		System.out.println("********************************************");
		for (int i = 500; i < 530; i++) {
			System.out.println("     Memory "+ i + ": " + ram.getMemAdressValue(new Binary(16, i).getBinaryValueAsStringIntern()) +
					" ¦ " +	conv.binToDez(ram.getMemAdressValue(new Binary(16, i).getBinaryValueAsStringIntern())));
		}
	}
}
