/**
 * 		Klasse welche den Speicher des Mini-Power-PC Projekts simuliert.
 * 		Es wird eine HashMap verwendet welche die Speicheradressen 001
 * 		bis 600 simuliert.
 */
package info.mini.power.pc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mathias Weigert & Miro Ljubicic
 *
 */
public class Memory {

	private Map<Integer, Integer> mem;

	public Memory () {
		mem = new HashMap<Integer, Integer>();
	}

	/**
	 *  Methode welche den kompletten Speicher des Mini-Power-PC flusht.
	 */
	public void flushMemory () {
		for (int i=1; i<601; i++) {
			mem.put(i, 0);
		}

	}

	/** 
	 *	Methode welche den Wert einer Speicheradresse als Wert in einem beliebigen Zahlensystem liefert.
	 *
	 *	@param adr Integer welche die Adresse der angefragten Speicherzelle enthält.
	 *	@param xxx Integer Basis des Ausgabezahlensystems.
	 * 	@return String welcher den Wert der Speicheradresse im Format des Ausgabezahlensystem beinhaltet.
	 */
	public String getXXXMemAdressValue (int adr, int xxx) {
		Converter conv = new Converter();
		String str = "";
		str = conv.intToXXX(mem.get(adr), xxx);
		return str;
	}

	/** 
	 *  Methode welche den Wert einer Speicheradresse als Wert im Dezimalsystem liefert.
	 * 
	 * 	@param adr Integer welche die Adresse der angefragten Speicherzelle enthält.
	 * 	@return String welcher den Wert der Speicheradresse als Dezimal Wert beinhaltet. 
	 */
	public String getDezimalMemAdressValue (int adr) {
		return String.valueOf(mem.get(adr));
	}

	/**
	 * Methode welche den Wert einer Speicherzelle setzt.
	 * 
	 * @param adr Integer welche die Adresse der entsprechenden Speicherzelle angibt.
	 * @param val Integer mit dem Wert der entsprechenden Speicherzelle.
	 */
	public void setMemoryValue (int adr, int val) {
		mem.put(adr, val);
	}
}
