/**
 * Klasse welche ein File Auswahlfenster �ffnet und das ausgew�hlte File �bergeben kann.
 */
package info.mini.power.pc;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Mathias Weigert & Miro Ljubicic
 *
 */
public class GUI_OpenFileDialog {

	private File file;
	
	/**
	 * Constructor welcher einen Dialog �ffnet mit welchen sich ein Assembler Program (*.apm)
	 * ausw�hlen l�sst.
	 * Das ausgew�hlte File wird in der Klasse gespeichert.
	 */
	public GUI_OpenFileDialog() {
		JFileChooser fc =  new JFileChooser();
		fc.setDialogTitle("Mini-Power-PC Assembler File Loader");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setApproveButtonText("Choose File");
		fc.setFileFilter(new FileNameExtensionFilter("Assembler (*.apm)", "apm"));
		int returnVal = fc.showOpenDialog(new JFrame());
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
		}
	}
	
	/**
	 * Methode welche das ausgew�hlte File zur�ck gibt.
	 * @return FILE enth�lt kompletten Pfad + File
	 */
	public File getChoosedFile() {
		return file;
	}
}
