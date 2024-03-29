/**
 * Klasse welche mittels einer Map aus Char ganzzahlige Bin�rzahlen simuliert und
 * erm�glicht diverse Rechenoperationen und Umwandlungen mit diesen durchzuf�hren.
 */
package info.mini.power.pc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mathias Weigert & Miro Ljubicic
 *
 */
public class Binary {

	Map<Integer, String> binary;
	Map<Integer, String> zweiercomp;
	int bit;
	boolean neg;
	boolean signed;
	int dezValue;


	public Binary(int bit, int val, boolean signed) {
		this.bit = bit;
		this.signed = signed;
		dezValue = val;
		//		if (this.bit % 8 == 0) {
		binary = new HashMap<Integer, String>(this.bit);
		intToBinary(dezValue);
		//		} else {
		//			System.out.println("Fehler beim generieren der Bin�rzahl, es muss ein vielfaches" +
		//			"von 8 als Bittiefe angegeben werden.");
		//		}
		if (signed) {
			if ((Math.pow(2, bit-1)*-1) < dezValue && dezValue < (Math.pow(2, bit-1)-1)) {
				zweiercomp = new HashMap<Integer, String>(this.bit);
				if (dezValue < 0) {
					binTo2erKomp(binary);
				} else {
					zweiercomp = binary;
				}
			} else {
				System.out.println("Fehler beim generieren des 2er Komplements, Wert ist f�r angegebene" +
						" Bittiefe zu gross. Bit: " + bit + " Val: " + val );
			}
		}
	}

	/**
	 * Methode welche einen ganzzahligen Integer Wert in eine Bin�rzahl umwandelt. Sollte
	 * es sich um einen negativen Wert handeln, so wird der Betrag umgewandelt und ein flag
	 * gesetzt.
	 * @param val Integer welche den Wert beinhaltet welcher umgewandelt werden soll.
	 */
	public void intToBinary (int val) {
		if (val < 0) {
			neg = true;
			val = val * -1;
		} else {
			neg = false;
		}
		for (int i = bit -1; i >= 0; i--) {
			binary.put(i,  String.valueOf(val % 2));
			val = ((val - (val % 2)) / 2);
		}
	}

	/**
	 * Methode welche einen String aus den einzelnen Elementen der Binary Map liefert.
	 * Ohne Formatierung zur Internen Weiterverarbeitung.
	 * @return String mit der Bin�rzahl. 
	 */
	public String getBinaryValueAsStringIntern() {
		String txt = "";
		for (int i = 0; i <= binary.size() - 1; i++) {
			txt += String.valueOf(binary.get(i));
		}
		return txt;
	}

	/**
	 * Methode welche einen String aus den einzelnen Elementen der Binary Map liefert.
	 * Formatiert, damit man immer 8 Bit a Block sieht.
	 * @return String mit der Bin�rzahl. 
	 */	
	public String getBinaryValueAsStringExtern() {
		String txt = "";
		Converter conv = new Converter();
		txt = conv.formatBinary(getBinaryValueAsStringIntern());
		return txt;	
	}

	/**
	 * Methode welche einen String aus den einzelnen Elementen der Zweiercomp Map liefert.
	 * Formatiert, damit man immer 8 Bit a Block sieht.
	 * @return String mit der Zweier Komplement Zahl.
	 */
	public String get2erKompValueAsStringExtern() {
		String txt = "";
		Converter conv = new Converter();
		txt = conv.formatBinary(get2erKompValueAsStringIntern());
		return txt;
	}

	/**
	 * Methode welche einen String aus den einzelnen Elementen der Zweiercomp Map liefert.
	 * Ohne Formatierung zur Internen Weiterverarbeitung.
	 * @return String mit der Zweier Komplement Zahl.
	 */
	public String get2erKompValueAsStringIntern() {
		String txt = "";
		if (signed) {
		for (int i = 0; i <= zweiercomp.size() - 1; i++) {
			txt += zweiercomp.get(i);
		}
		
		} else {
			System.out.println("Es wird eine signed Ausgabe von einer unsigned Variable verlangt.");
		}
		return txt;
	}

	/**
	 * Methode welche true zur�ckgibt, wenn es sich um eine negative Zahl handelt.
	 * @return Boolean welches bei einer positiven Zahl false und bei einer negativen
	 * 		   Zahl true zur�ckgibt.
	 */
	public boolean isNegative() {
		return neg;
	}

	/**
	 * Methode wandelt einen String welcher eine Bin�rzahl enth�lt in eine Bin�rzahl
	 * in 2er Komplement�r Darstellung um.
	 * @param binary2 String welcher die zu umwandelnde Bin�rzahl enth�lt.
	 */
	public void binTo2erKomp (Map<Integer, String> binary2) {
		int merker = 1;
		for (int i =  binary2.size() - 1; i >0; i--) {
			if (binary2.get(i).equals("1")) {
				zweiercomp.put(i, "0");
			} else {
				if (binary2.get(i).equals("0")) {
					zweiercomp.put(i, "1");
				}
			}
		}
		for (int i= zweiercomp.size() ; i > 0; i--) {
			if (merker == 1) {
				if (zweiercomp.get(i).equals("1")) {
					merker = 1;
					zweiercomp.put(i, "0");
				} else {
					merker = 0;
					zweiercomp.put(i, "1");
				}
			}
		}
		if (neg) {
			zweiercomp.put(0, "1");
		} else {
			zweiercomp.put(0, "0");
		}
	}
	
	/**
	 * Methode liefert ein True zur�ck wenn es sich um eine Zahl im 2er Komplement handelt
	 * @return TRUE wenn Bin�rzahl im 2er Komplement vorliegt.
	 */
	public boolean isSigned(){
		return signed;
	}
}
