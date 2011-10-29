/**
 * 
 */
package info.mini.power.pc;

/**
 * @author Mathias Weigert & Miro Ljubicic
 *
 */
public class Converter {
	
	/**
	 * Methode welche einen ganzzahligen Integer Wert in einen Wert eines beliebigen Zahlensystem umwandelt.
	 * @param val Integer welche den Wert beinhaltet welcher umgewandelt werden soll.
	 * @param xxx Integer welche die Basis des Zielzahlensystems beinhaltet (z.B. 16 für Hexadezimalsystem).
	 * @return String welcher den umgewandelten Wert als String zurückgibt.
	 */
	public String intToXXX (int val, int xxx) {
		String tmp = "";
		while (val >= xxx) {
			tmp += String.valueOf(val % xxx);
			val = (val - (val % xxx)) / xxx;
		}
		String str = "";
		for (int i = tmp.length() - 1; i >= 0; i--) {
			str += tmp.charAt(i);
		}
		return str;
	}
	
}
