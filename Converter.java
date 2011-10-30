/**
 * Klasse welche die verchiedensten Umrechnungen von einem Zahlensystem zu einem anderen durchf�hrt. 	
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
	 * @param xxx Integer welche die Basis des Zielzahlensystems beinhaltet (z.B. 16 f�r Hexadezimalsystem).
	 * @return String welcher den umgewandelten Wert als String zur�ckgibt.
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
	
	/**
	 * Methode welche einen String (welcher eine ganzzahlige Bin�rzahl enth�lt) in eine Dezimalzahl umrechnet.
	 * @param str String welcher eine ganzzahlige Bin�rzahl enth�lt.
	 * @return Integer welcher den umgerechneten Wert des Eingabe Strings enth�lt.
	 */
	public int binToDez (String str) {
		int val = 0;
		int max = str.length() - 1;
		for (int i = 0; i <= max; i++) {
			val += Math.pow(Integer.valueOf(str.charAt(max - 1)), i);
		}
		return val;
	}
	
	/**
	 * Methode welche eine Bin�rzahl in die Darstellung des 2er Komplements umrechnet.
	 * @param str String welcher die zu umrechnende Bin�rzahl enth�lt
	 * @return String welcher die Bin�rzahl im 2er Komplemt enth�lt.
	 */
	public String binTo2erKomp (String str) {
		String s = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			if 
		}
		return s;
	}
}
