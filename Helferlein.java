package info.mini.power.pc;

public class Helferlein {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Binary b = new Binary(16, 100);
//		System.out.println("  Dezimal: 100");
//		System.out.println("    Binär: " + b.getBinaryValueAsString());
//		System.out.println("2er Komp.: " + b.get2erKompValueAsString());
//		Binary bin = new Binary(16, 26);
//		System.out.println("  Dezimal: 26");
//		System.out.println("    Binär: " + bin.getBinaryValueAsString());
//		System.out.println("2er Komp.: " + bin.get2erKompValueAsString());
//		Binary b2 = new Binary(16, 15);
//		System.out.println("  Dezimal: 15");
//		System.out.println("    Binär: " + b2.getBinaryValueAsString());
//		System.out.println("2er Komp.: " + b2.get2erKompValueAsString());
		
//		AssemblerCompiler comp = new AssemblerCompiler();
//		System.out.println(comp.compileMnemonic("LWDD 01, #204") + " - LWDD 01, #204");
//		System.out.println(comp.compileMnemonic("BCD #26") + " - BCD #26");
//		System.out.println(comp.compileMnemonic("ADDD #15") + " - ADDD #15");
//		System.out.println(comp.compileMnemonic("NOT") + " - NOT");
//		System.out.println("Erstelle 10 neue Register und liefere den aktuelen Wert:");
		
//		for (int i = 0; i<= 9; i++) {
//			System.out.println(new Register().getRegisterValue());
//		}
//		System.out.println();
//		System.out.println(new Converter().binToDez("01100100"));
		CentralProcessingUnit cpu = new CentralProcessingUnit();
		cpu.statusOutput();
		cpu.initSystem();
		cpu.increaseCounter();
		cpu.waitForUserInput();
		cpu.statusOutput();
	}

}
