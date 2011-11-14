package info.mini.power.pc;

import java.io.FileNotFoundException;

public class Helferlein {

	/** Klasse welche die verschiedenen Funktionalitäten der einzelnen Klassen/Methoden
	 *  testet.
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
//		cpu.executeNextCommand();
//		cpu.statusOutput();
		cpu.initSystem();
//		cpu.increaseCounter();
//		cpu.waitForUserInput();
//		cpu.executeNextCommand();
		cpu.statusOutput();
		cpu.increaseCounter();
		try {
			cpu.loadProgramToMemory();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cpu.statusOutput();
//		GUI_OpenFileDialog fd = new GUI_OpenFileDialog();
//		System.out.println(fd.getChoosedFile());
//		Loader loader = new Loader();
//		try {
//			loader.loadFileInMemory(fd.getChoosedFile());
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
