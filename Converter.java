/**
 * Klasse welche die verchiedensten Umrechnungen von einem Zahlensystem zu einem anderen durchführt. 	
 */
package info.mini.power.pc;

/**
 * @author Mathias Weigert & Miro Ljubicic
 *
 */
public class Converter {


	/**
	 * Methode welche einen String (welcher eine ganzzahlige Binärzahl enthält) in eine Dezimalzahl umrechnet.
	 * @param str String welcher eine ganzzahlige Binärzahl enthält.
	 * @return Integer welcher den umgerechneten Wert des Eingabe Strings enthält.
	 */
	public int binToDez (String str) {
		int val = 0;
		if (str != null) {
			int max = str.length() - 1;
			for (int i = str.length() - 1; i >= 0; i--) {
				double x = Math.pow(2, i);
				val = (int) (val + (x * Integer.parseInt(str.substring(max - i, max - i + 1))));
			}
		}
		return val;
	}
	
	public String formatBinary (String str) {
		String fstr = "";
		return fstr;
	}
}
