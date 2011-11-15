/**Klasse welche die Main Methode enthält und die komplette Simulation des 
 * Mini-Power-PC übernimmt.
 * 
 */
package info.mini.power.pc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * @author Mathias Weigert & Miro Ljubicic
 *
 */
public class MiniPowerPC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		char ch;
		
		// TODO Auto-generated method stub
		CentralProcessingUnit cpu = new CentralProcessingUnit();
		cpu.statusOutput();
		cpu.initSystem();
		cpu.increaseCounter();
		cpu.statusOutput();
		try {
			cpu.loadProgramToMemory();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cpu.increaseCounter();
		cpu.statusOutput();
		cpu.waitForUserInput();
		cpu.increaseCounter();
		cpu.statusOutput();
	}

}
