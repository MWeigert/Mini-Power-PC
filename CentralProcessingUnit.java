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

	public void statusOutput() {
		Converter conv = new Converter();
		System.out.println("************ CPU Systemstatus **************");
		System.out.println("                 Step: " + counter);
		System.out.println("********************************************");
		System.out.println("Befehlsregister: " + breg.getRegisterValue() + " ¦ " + conv.binToDez(breg.getRegisterValue()));
		System.out.println("    Akkumulator: " + akku.getRegisterValue() + " ¦ " + conv.binToDez(akku.getRegisterValue()));
		System.out.println("     Register 1: " + reg1.getRegisterValue() + " ¦ " + conv.binToDez(reg1.getRegisterValue()));
		System.out.println("     Register 2: " + reg2.getRegisterValue() + " ¦ " + conv.binToDez(reg2.getRegisterValue()));
		System.out.println("     Register 3: " + reg3.getRegisterValue() + " ¦ " + conv.binToDez(reg3.getRegisterValue()));
		System.out.println("********************************************");
		System.out.println("  Befehlszähler: " + bcount + " ¦ " + conv.binToDez(bcount));
		System.out.println("      Carry Bit: " + cbit);
		System.out.println("********************************************");
		System.out.println("*************Memory 500 bis 5029************");
		System.out.println("********************************************");
		for (int i = 500; i < 530; i++) {
			System.out.println("     Memory "+ i + ": " + ram.getMemAdressValue(new Binary(16, i).getBinaryValueAsStringIntern()) +
					" ¦ " +	conv.binToDez(ram.getMemAdressValue(new Binary(16, i).getBinaryValueAsStringIntern())));
		}
		System.out.println("********************************************");
	}
}
