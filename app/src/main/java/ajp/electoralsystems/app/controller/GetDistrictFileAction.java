package ajp.electoralsystems.app.controller;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import ajp.electoralsystems.app.io.DistrictFileReader;
import ajp.electoralsystems.app.view.MainWindow;
import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.i18n.Messages;

/**
 * @author Andres Jimenez Penalver
 */
public class GetDistrictFileAction implements Action {
	
	private MainWindow mainWindow;
	
	public GetDistrictFileAction(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	/**
	 * Returns a district
	 * 
	 * @return a District object
	 * @throws AppException
	 */
	public Object execute(Object object) throws AppException {
		District district = null;
		File file=null;
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle(Messages.getString("FileChooser.Open"));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setCurrentDirectory(new File(System.getProperties().getProperty("user.dir")));

            int retval = chooser.showOpenDialog(null);
            if (retval == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();
                district = new DistrictFileReader().read(file);
            }
    
        } catch (AppException e) {
        	JOptionPane.showMessageDialog(null, e.getMessage(), Messages.getFormattedString("Error.GettingFile", file.getName()), JOptionPane.ERROR_MESSAGE);
        	
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, e.getMessage(), Messages.getFormattedString("Error.GettingFile", file.getName()), JOptionPane.ERROR_MESSAGE);			        
        }
        return district;
		
	}

}
