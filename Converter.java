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
	 * Methode welche einen String (welcher eine ganzzahlige Bin�rzahl enth�lt) in eine Dezimalzahl umrechnet.
	 * @param str String welcher eine ganzzahlige Bin�rzahl enth�lt.
	 * @return Integer welcher den umgerechneten Wert des Eingabe Strings enth�lt.
	 */
	public int binToDez (String str) {
		int val = 0;
		if (str != null && !str.equals("undefined")) {
			int max = str.length() - 1;
			for (int i = str.length() - 1; i >= 0; i--) {
				double x = Math.pow(2, i);
				val = (int) (val + (x * Integer.parseInt(str.substring(max - i, max - i + 1))));
			}
		}
		return val;
	}

	/**
	 * Methode welche eine Bin�rzahl, welche in 2er Komplementdarstellung vorliegt in eine Dezimalzahl umwandelt.
	 * @param str STRING welcher die Zahl in 2er Kompelementdarstellung beinhaltet.
	 * @return INTEGER mit dem Wert der �bergebenen 2er Komplement Zahl.
	 */
	public int kompToInt(String str) {
		int value = 0;
		String bin ="";
		if (str.charAt(0) == '1') {
			str = str.substring(1);
			for (int i = 0; i <= str.length() - 1; i++) {
				if (str.charAt(i) == '1') bin += "0";
				else bin += "1";
			}
			int merker = 1;
			str = "";
			for (int i = bin.length() - 1; i >= 0; i--) {
				if (bin.charAt(i) == '0') {
					if (merker == 1) {
						str += "1";
						merker = 0;
					}  else str += "0";
				} else {
					if (merker == 1) str += "0";
					else str += "1";
				}
			}
			bin = "";
			for (int i = str.length() - 1; i >= 0; i--) {
				bin += str.charAt(i);
			}
			value = binToDez(bin) * (-1);
		} else value = binToDez(str.substring(1));
		return value;
	}

	/**
	 * Methode welche eine Bin�rzahl "formatiert" (d.h. Byteweise) ausgibt. 
	 * @param str STRING welcher die umzuwandelte Bin�rzahl enth�lt. 
	 * @return STRING Byte weise formatierte Bin�rzahl.
	 */
	public String formatBinary (String str) {
		String fstr = "";
		for (int i = 0; i <= str.length() - 1; i++) {
			if (i % 8 == 0 && i > 0 ) {
				fstr += " ";
			}
			fstr += String.valueOf(str.charAt(i));
		}
		return fstr;
	}
}
