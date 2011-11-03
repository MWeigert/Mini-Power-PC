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
			if (mne.equals("INC")) mcode = "00000001 00000000";
			if (mne.equals("DEC")) mcode = "00000100 00000000";
			if (mne.equals("SRA")) mcode = "00000101 00000000";
			if (mne.equals("SLA")) mcode = "00001000 00000000";
			if (mne.equals("SRL")) mcode = "00001001 00000000";
			if (mne.equals("SLL")) mcode = "00001100 00000000";
			if (mne.equals("NOT")) mcode = "00000000 00000000";
			break;
		case 1:
			mcode = "0001" + mne.substring(3) + "00 00000000";
			break;
		case 2:
			if (mne.startsWith("OR")) mcode = "0000" + mne.substring(4) + "11 00000000";
			if (mne.startsWith("BZ")) mcode = "0001" + mne.substring(4) + "10 00000000";
			if (mne.startsWith("BC")) mcode = "0001" + mne.substring(4) + "11 00000000";
			if (mne.startsWith("BD")) {
				Binary bin = new Binary(16, Integer.valueOf(mne.substring(4)));
				mcode = "001000" + bin.getBinaryValueAsString().substring(6);
			}
			break;
		case 3:
			if (mne.startsWith("CLR")) mcode = "0000" + mne.substring(5) + "10 10000000";
			if (mne.startsWith("ADD")) mcode = "0000" + mne.substring(5) + "11 10000000";
			if (mne.startsWith("AND")) mcode = "0000" + mne.substring(5) + "10 00000000";
			break;
		default:
			System.out.println("Fehler beim compilen von: " + mne);
			break;
		}
		return mcode;
	}

}
