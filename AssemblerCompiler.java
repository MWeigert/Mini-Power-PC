/**
 * Klasse welche die eingegebenen Mnemonics in Maschinencode umwandelt und zurück gibt.
 */
package info.mini.power.pc;

/**
 * @author Mathias Weigert & Miro Ljubicic
 *
 */
public class AssemblerCompiler {

	/**
	 * Methode welche einen als Mnemonic eingegebenen Assembler Befehl in Maschinencode 
	 * umwandelt und zurück gibt. Sollte der Befehl nicht compilierbar sein wird eine
	 * Fehlermeldung ausgegeben.
	 * @param mne String welcher den Assemblercode mit Mnemonic beinhaltet
	 * @return String mit Befehl im Maschinencode
	 */
	public String compileMnemonic (String mne) {
		int x = mne.indexOf(' ');
		String mcode = "";
		switch (x) {
		case -1:
			if (mne.equals("INC")) mcode = "0000000100000000";
			if (mne.equals("DEC")) mcode = "0000010000000000";
			if (mne.equals("SRA")) mcode = "0000010100000000";
			if (mne.equals("SLA")) mcode = "0000100000000000";
			if (mne.equals("SRL")) mcode = "0000100100000000";
			if (mne.equals("SLL")) mcode = "0000110000000000";
			if (mne.equals("NOT")) mcode = "0000000010000000";
			break;
		case 1:
			mcode = "0001" + mne.substring(2) + "0000000000";
			break;
		case 2:
			if (mne.startsWith("OR")) mcode = "0000" + mne.substring(3) + "1100000000";
			if (mne.startsWith("BZ")) mcode = "0001" + mne.substring(3) + "1000000000";
			if (mne.startsWith("BC")) mcode = "0001" + mne.substring(3) + "1100000000";
			if (mne.startsWith("BD")) {
				Binary bin = new Binary(16, Integer.valueOf(mne.substring(3)), false);
				mcode = "001000" + bin.getBinaryValueAsStringExtern().substring(6);
			}
			break;
		case 3:
			if (mne.startsWith("CLR")) mcode = "0000" + mne.substring(4) + "1010000000";
			if (mne.startsWith("ADD")) mcode = "0000" + mne.substring(4) + "1110000000";
			if (mne.startsWith("AND")) mcode = "0000" + mne.substring(4) + "1000000000";
			if (mne.startsWith("BNZ")) mcode = "0001" + mne.substring(4) + "0100000000";
			if (mne.startsWith("BZD")) {
				Binary bin = new Binary(16, Integer.valueOf(mne.substring(4)), false);
				mcode = "001100" + bin.getBinaryValueAsStringIntern().substring(6);
			}
			if (mne.startsWith("BCD")) {
				Binary bin = new Binary(16, Integer.valueOf(mne.substring(5)), false);
				mcode = "001110" + bin.getBinaryValueAsStringIntern().substring(6);
			}
			break;
		case 4:
			if (mne.startsWith("ADDD")) {
				Binary bin  = new Binary(16, Integer.valueOf(mne.substring(6)), true);
				char vz = '0';
				if (bin.isNegative()) vz = '1';
				mcode = "1" + vz + bin.get2erKompValueAsStringIntern().substring(2);
			}
			if (mne.startsWith("LWDD")) {
				int p = mne.indexOf("#");
				Binary bin = new Binary(16, Integer.valueOf(mne.substring(p + 1)), false);
				mcode = "0100" + mne.substring(5, 7) + bin.getBinaryValueAsStringIntern().substring(6);
			}
			if (mne.startsWith("SWDD")) {
				int q = mne.indexOf("#");
				Binary bin = new Binary(16, Integer.valueOf(mne.substring(q + 1)), false);
				mcode = "0110" + mne.substring(5, 7) + bin.getBinaryValueAsStringIntern().substring(6);
			}
			if (mne.startsWith("BNZD")) {
				Binary bin = new Binary(16, Integer.valueOf(mne.substring(6)), false);
				mcode = "001010" + bin.getBinaryValueAsStringIntern().substring(6);
			}
			break;
		default:
			System.out.println("Fehler beim compilen von: " + mne);
			break;
		}
		return mcode;
	}
}
