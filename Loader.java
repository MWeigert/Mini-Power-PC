/**
 * Klasse welche das Assembler Programm von File in den Befehlsspeicher l�dt.
 * Dazu wird der AssemblerCompiler benutzt.
 */
package info.mini.power.pc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Mathias Weigert & Miro Ljubicic
 *
 */
public class Loader {

	/**
	 * Methode welche ein File (Assembler Program) �ffnet und das Programm Zeile f�r Zeile
	 * durch den AssemblerCompiler als MaschinenCode in den Speicher (Memory) schreibt und
	 * wieder zur�ck gibt. 
	 * @param file FILE welches das Assembler Program enth�lt.
	 * @return MEMORY welches das geladene Assembler Program als Maschinencode enth�lt.
	 * @throws FileNotFoundException
	 */
	public Memory loadFileInMemory (File file) throws FileNotFoundException {
		Memory mem = new Memory();
		AssemblerCompiler compiler = new AssemblerCompiler();
		int adr = 100;
		String str = "";
		String mcode = "";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		try {
			int i = 0;
			while ((str = reader.readLine()) != null) {
				i++;
				mcode = compiler.compileMnemonic(str);
				mem.setMemoryValue(new Binary(16, adr, false).getBinaryValueAsStringIntern(), mcode.substring(0, 8));
				adr++;
				mem.setMemoryValue(new Binary(16, adr, false).getBinaryValueAsStringIntern(), mcode.substring(8));
				adr++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mem;
	}
}
