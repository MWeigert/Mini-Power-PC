/**Klasse welche die Main Methode enthält und die komplette Simulation des 
 * Mini-Power-PC übernimmt.
 * 
 */
package info.mini.power.pc;

import java.io.FileNotFoundException;

/**
 * @author Mathias Weigert & Miro Ljubicic
 *
 */
public class MiniPowerPC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
