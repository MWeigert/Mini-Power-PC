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
		if (str != null && !str.equals("undef")) {
			int max = str.length() - 1;
			for (int i = str.length() - 1; i >= 0; i--) {
				double x = Math.pow(2, i);
				val = (int) (val + (x * Integer.parseInt(str.substring(max - i, max - i + 1))));
			}
		}
		return val;
	}

	/**
	 * Methode welche eine Binärzahl, welche in 2er Komplementdarstellung vorliegt in eine Dezimalzahl umwandelt.
	 * @param str STRING welcher die Zahl in 2er Kompelementdarstellung beinhaltet.
	 * @return INTEGER mit dem Wert der Übergebenen 2er Komplement Zahl.
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
	 * Methode welche eine Binärzahl "formatiert" (d.h. Byteweise) ausgibt. 
	 * @param str STRING welcher die umzuwandelte Binärzahl enthält. 
	 * @return STRING Byte weise formatierte Binärzahl.
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

	/**
	 * Methode welche genau das Gegenteil des Assembler Compilers macht und aus einem MAschinencode
	 * wieder das passende Mnemonic erstellt.
	 * @param mcode STRING welcher den MaschinenCode welcher umgewandelt werden soll enthält.
	 * @return STRING mit dem Mnemonic.
	 */
	public String mcToMnemonic( String mcode) {
		String mnemonic = "Speicherbereich leer";
		if (!mcode.equals("undef")){
			int x = mcode.indexOf("1");
			int value = 0;
			switch (x) {
			case 0:
				value = new Converter().kompToInt(mcode.substring(1));
				mnemonic = "02 ADDD #"+ value;
				break;
			case 1:
				value = new Converter().binToDez(mcode.substring(6));
				if (mcode.charAt(2) == '0') {
					mnemonic = "05 LWDD " + mcode.substring(4, 6) + ", #" + value;
				} else {
					mnemonic = "06 SWDD " + mcode.substring(4, 6) + ", #" + value;
				}
				break;
			case 2:
				value = new Converter().binToDez(mcode.substring(6));
				if (mcode.charAt(3) == '0') {
					if (mcode.charAt(4) == '0') {
						mnemonic = "21 BD #" + value;
					} else mnemonic = "19 BNZD #" + value;
				} else {
					if (mcode.charAt(4) == '0') {
						mnemonic = "18 BZD #" + value;
					}  else mnemonic = "20 BCD #" + value;
				}
				break;
			case 3:
				if (mcode.charAt(6) == '0') {
					if (mcode.charAt(7) == '0') {
						mnemonic = "17 B " + mcode.substring(4, 6);
					} else mnemonic = "15 BNZ " + mcode.substring(4, 6);
				} else {
					if (mcode.charAt(7) == '0') {
						mnemonic = "14 BZ " + mcode.substring(4, 6);
					} else mnemonic = "16 BC " + mcode.substring(4, 6);
				}
				break;
			case 8:
				mnemonic = "13 NOT";
				break;
			default:
				String sub = mcode.substring(6, 9);
				x = sub.indexOf("0");
				switch (x) {
				case -1:
					mnemonic = "01 ADD " + mcode.substring(4, 6);
					break;
				case 1:
					if (sub.charAt(2) == '0') {
						mnemonic = "11 AND " + mcode.substring(4, 6);
					} else mnemonic = "00 CLR " + mcode.substring(4, 6);
					break;
				case 2:
					mnemonic = "12 OR " + mcode.substring(4, 6);
					break;
				default:
					sub = mcode.substring(4, 8);
					x = sub.indexOf("1");
					switch (x) {
					case 0: 
						if (sub.charAt(1) == '1') {
							mnemonic = "10 SLL";
						} else {
							if (sub.charAt(3) == '0') mnemonic = "08 SLA";
							else mnemonic = "09 SRL";
						} 
						break;
					case 1:
						if (sub.charAt(3) == '0') mnemonic = "04 DEC";
						else mnemonic = "07 SRA";
						break;
					case 3:
						mnemonic = "03 INC";
						break;
					default:
						System.out.println("Fehler bei der Umwandlung von " + mcode + " in Mnemonics.");
						break;
					}
				}
				break;
			}
		}
		return mnemonic;
	}
}
