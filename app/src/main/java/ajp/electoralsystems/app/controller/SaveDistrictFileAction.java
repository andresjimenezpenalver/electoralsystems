package ajp.electoralsystems.app.controller;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ajp.electoralsystems.app.io.DistrictFileWriter;
import ajp.electoralsystems.app.view.MainWindow;
import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.i18n.Messages;

/**
 * @author Andres Jimenez Penalver
 */
public class SaveDistrictFileAction implements Action {
	
	private MainWindow mainWindow;
	
	public SaveDistrictFileAction(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	/**
	 * Saves a district in a file
	 * 
	 * @param object the district to save
	 * @throws AppException
	 */
	public Object execute(Object object) throws AppException {
		File file=null;
		District district = (District) object;
		JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(Messages.getString("FileChooser.Save"));
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setCurrentDirectory(new File(System.getProperties().getProperty("user.dir")));

        int retval = chooser.showSaveDialog(null);
        if (retval == JFileChooser.APPROVE_OPTION) {
            try {
                file = chooser.getSelectedFile();
                DistrictFileWriter writer = new DistrictFileWriter();
        		writer.write(district, file);
                
            } catch (AppException e) {
            	JOptionPane.showMessageDialog(null, Messages.getFormattedString("Error.SavingFile", file.getPath()), Messages.getString("Error"), JOptionPane.ERROR_MESSAGE);
            	
            } catch (Exception e) {
            	JOptionPane.showMessageDialog(null, Messages.getFormattedString("Error.SavingFile", file.getPath()), Messages.getString("Error"), JOptionPane.ERROR_MESSAGE);			        
            }
        }				
        return null;
	}	

}
