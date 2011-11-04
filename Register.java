/**
 * 	Klasse welche alle Funktionalit�ten eines Registers enth�lt.
 *  Sie wird sp�ter f�r die Register 1 bis 3, den Akkumulator und
 * 	das Befehlsregister genutzt werden.
 * 
 *  Ein Register besteht aus einem String welcher eine 16 Bit 
 * 	Bin�rzahl enth�lt.
 */
package info.mini.power.pc;

import java.util.Random;

/**
 * @author Mathias Weigert & Miro Ljubicic
 *
 */
public class Register {

	String value;
	
	public Register() {
		Random ran = new Random();
		value = "";
		for (int i = 0; i <= 15; i++) {
			value += String.valueOf(Math.abs(ran.nextInt() % 2));
		}
	}
	
	/**
	 * Methode welche den Wert des Registers zur�ckliefert.
	 * @return String welcher den gespeicherten Wert des Registers als Bin�rzahl enth�lt.
	 */
	public String getRegisterValue() {
		return value;
	}
	
	/**
	 * Methode welche den Bin�rwert des Registers setzt.
	 * @param str String welcher den Bin�rwert enth�lt welcher in diesem Register gesetzt werden
	 * 			  soll.
	 */
	public void setRegisterValue(String str) {
		value = str;
	}
	
	/**
	 * Methode welche den Wert des Registers auf 0000000000000000 setzt.
	 */
	public void flushRegister() {
		value = "0000000000000000";
	}
}
