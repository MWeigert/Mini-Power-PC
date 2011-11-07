/**	Klasse welche die beiden Zahlen welche Multipliziert werden sollen einliest
 * 	und die Daten in den Speicher (Adresse 500/501 und 502/503) schreibt.
 * 
 */
package info.mini.power.pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Mathias Weigert & Miro Ljubicic
 *
 */
public class UserInput {

	/**
	 * Methode welche über die Console zwei ganzahlige Integer Werte einliest und in dem 
	 * ihr übergebenen Speicher an die Adressen 500 & 501 bzw. 502 & 503 schreibt. Anschliessend
	 * wird der Speicher wieder zurückgegeben.
	 * @param mem Memory "Speicher" in welchen die beiden Userwerte an die vorgegebenen Adressen
	 * 			  speichert.
	 * @return Memory Gibt den geänderten "Speicher" wieder zurück.
	 */
	public Memory userInput(Memory mem) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int a = 0;
		int b = 0;
		System.out.println("Bitte geben Sie die den Ersten Multiplikator ein: ");
		try {
			a = Integer.valueOf(reader.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Bitte geben Sie den Zweiten Multiplikator ein: ");
		try {
			b = Integer.valueOf(reader.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Binary ba = new Binary(16, a);
		mem.setMemoryValue(new Binary(16, 500).getBinaryValueAsStringIntern(), 
				ba.get2erKompValueAsStringIntern().substring(0, 7));
		mem.setMemoryValue(new Binary(16, 501).getBinaryValueAsStringIntern(), 
				ba.get2erKompValueAsStringIntern().substring(8, 15));
		Binary bb = new Binary(16, b);
		mem.setMemoryValue(new Binary(16, 502).getBinaryValueAsStringIntern(), 
				bb.get2erKompValueAsStringIntern().substring(0, 7));
		mem.setMemoryValue(new Binary(16, 503).getBinaryValueAsStringIntern(), 
				bb.get2erKompValueAsStringIntern().substring(8, 15));
		return mem;
	}
}
