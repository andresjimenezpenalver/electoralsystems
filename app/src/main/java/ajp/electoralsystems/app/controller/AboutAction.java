package ajp.electoralsystems.app.controller;

import java.awt.Component;

import javax.swing.JOptionPane;

import ajp.electoralsystems.app.view.MainWindow;
import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.i18n.Messages;

/**
 * @author Andres Jimenez Penalver
 */
public class AboutAction implements Action {

	private MainWindow mainWindow;
	
	public AboutAction(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	/**
	 * 
	 */
	public Object execute(Object object) throws AppException {
		String message = Messages.getString("DevelopedBy");
		String title = Messages.getString("About");
		JOptionPane.showMessageDialog((Component) null, message, title, JOptionPane.INFORMATION_MESSAGE);
		return null;
	}
	
}
