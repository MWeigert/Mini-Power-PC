/**
 * Klasse welche mittels einer Map aus Char ganzzahlige Binärzahlen simuliert und
 * ermöglicht diverse Rechenoperationen und Umwandlungen mit diesen durchzuführen.
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
	int dezValue;


	public Binary(int bit, int val) {
		this.bit = bit;
		dezValue = val;
//		if (this.bit % 8 == 0) {
			binary = new HashMap<Integer, String>(this.bit);
			intToBinary(val);
//		} else {
//			System.out.println("Fehler beim generieren der Binärzahl, es muss ein vielfaches" +
//			"von 8 als Bittiefe angegeben werden.");
//		}
		if ((Math.pow(2, bit)*-1) < dezValue && dezValue < (Math.pow(2, bit)-1)) {
			zweiercomp = new HashMap<Integer, String>(this.bit);
			intTo2erKomp(binary);
		} else {
			System.out.println("Fehler beim generieren des 2er Komplements, Wert ist für angegebene" +
			" Bittiefe zu gross.");
		}
	}

	/**
	 * Methode welche einen ganzzahligen Integer Wert in eine Binärzahl umwandelt. Sollte
	 * es sich um einen negativen Wert handeln, so wird der Betrag umgewandelt und ein flag
	 * gesetzt.
	 * @param val Integer welche den Wert beinhaltet welcher umgewandelt werden soll.
	 * @return String welcher den umgewandelten Wert als String zurückgibt.
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
	 * @return String mit der Binärzahl. 
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
	 * @return String mit der Binärzahl. 
	 */	
	public String getBinaryValueAsStringExtern() {
		String txt = "";
		for (int i = 0; i <= binary.size() - 1; i++) {
			if (i % 8 == 0 && i > 0 ) {
				txt += " ";
			}
			txt += String.valueOf(binary.get(i));
		}
		return txt;	
	}

	/**
	 * Methode welche einen String aus den einzelnen Elementen der Zweiercomp Map liefert.
	 * @return String mit der Zweier Komplement Zahl.
	 */
	public String get2erKompValueAsString() {
		String txt = "";
		for (int i = 0; i <= zweiercomp.size() - 1; i++) {
			if (i % 8 == 0 && i > 0) {
				txt += " ";
			}
			txt += zweiercomp.get(i);
		}
		return txt;
	}

	/**
	 * Methode welche true zurückgibt, wenn es sich um eine negative Zahl handelt.
	 * @return Boolean welches bei einer positiven Zahl false und bei einer negativen
	 * 		   Zahl true zurückgibt.
	 */
	public boolean isNegative() {
		return neg;
	}

	/**
	 * Methode wandelt einen String welcher eine Binärzahl enthält in eine Binärzahl
	 * in 2er Komplementär Darstellung um.
	 * @param binary2 String welcher die zu umwandelnde Binärzahl enthält.
	 */
	public void intTo2erKomp (Map<Integer, String> binary2) {
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
}
