/**Klasse welche die Main Methode enthält und die komplette Simulation des 
 * Mini-Power-PC übernimmt.
 * 
 */
package info.mini.power.pc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
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
		boolean run = true;
		String keypressed = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
		while (run) {
			cpu.executeNextCommand();
			cpu.increaseCounter();
			cpu.statusOutput();
			if (new Converter().binToDez(cpu.getRAMadressValueWord(cpu.getBcountAsString())) == 0) run = false;
			System.out.println("Bitte Enter drücken um mit der ausführung fortzufahren.");
			try {
				keypressed = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cpu.statusOutput();
		System.out.println("---------- Assembler Programm abgearbeitet! ----------");
	}

}
