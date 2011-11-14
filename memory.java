/**
 * 		Klasse welche den Speicher des Mini-Power-PC Projekts simuliert.
 * 		Es wird eine HashMap verwendet welche die Speicheradressen 00000000 00000000
 * 		bis 11111111 11111111 (1 KiB) simuliert.
 */
package info.mini.power.pc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mathias Weigert & Miro Ljubicic
 *
 */
public class Memory {

	private Map<String, String> mem;

	public Memory () {
		mem = new HashMap<String, String>();
	}

	/**
	 *  Methode welche den kompletten Speicher des Mini-Power-PC flusht.
	 */
	public void flushMemory () {
		for (int i=0; i<1024; i++) {
			Binary bin = new Binary(10, i, false);
			mem.put(bin.getBinaryValueAsStringIntern(), "00000000");
		}
	}

	/** 
	 *	Methode welche den Wert einer Speicheradresse liefert.
	 *
	 *	@param adr String welcher die Adresse (Binärzahl) der angefragten Speicherzelle enthält.
	 * 	@return String welcher den Wert der Speicheradresse als Binärzahl beinhaltet.
	 */
	public String getMemAdressValue (String adr) {
		return mem.get(adr);
	}

	/** 
	 *	Methode welche den Wert eines Wortes einer Speicheradresse liefert.
	 *
	 *	@param adr String welcher die Adresse (Binärzahl) der angefragten Speicherzelle enthält.
	 * 	@return String welcher den Wort-Wert der Speicheradresse als Binärzahl beinhaltet.
	 */
	public String getMemAdressValueWord (String adr) {
		Converter conv = new Converter();
		String next = new Binary(16, conv.binToDez(adr) + 1, false).getBinaryValueAsStringIntern();
		if (mem.get(adr)==null || mem.get(next)==null ) {
			return "undef";
		}
		else {
			return mem.get(adr) + mem.get(next);
		}
	}

	/** 			
	 *  Methode welche den Wert einer Speicheradresse als Wert im Dezimalsystem liefert.
	 * 
	 * 	@param adr String welche die Binär-Adresse der angefragten Speicherzelle enthält.
	 * 	@return Integer welcher den Wert der Speicheradresse als Dezimal Wert beinhaltet. 
	 */
	public int getDezimalMemAdressValue (String adr) {
		Converter conv = new Converter();
		return conv.binToDez(mem.get(adr));
	}

	/**
	 * Methode welche den Wert einer Speicherzelle setzt.
	 * 
	 * @param adr String welcher die Adresse (Binärzahl) der entsprechenden Speicherzelle angibt.
	 * @param val String mit dem Binär-Wert der entsprechenden Speicherzelle.
	 */
	public void setMemoryValue (String adr, String val) {
		mem.put(adr, val);
	}
}
