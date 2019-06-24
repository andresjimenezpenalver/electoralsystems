package ajp.electoralsystems.app.controller;

import ajp.electoralsystems.app.view.MainWindow;
import ajp.electoralsystems.core.exception.AppException;

/**
 * @author Andres Jimenez Penalver
 */
public class ExitAction implements Action {

	private MainWindow mainWindow;
	
	public ExitAction(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	/**
	 * 
	 */
	public Object execute(Object object) throws AppException {
		System.exit(0);
		return null;
	}
	
}
