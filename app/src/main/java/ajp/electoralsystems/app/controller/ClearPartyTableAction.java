package ajp.electoralsystems.app.controller;

import ajp.electoralsystems.app.view.MainWindow;
import ajp.electoralsystems.core.exception.AppException;

/**
 * @author Andres Jimenez Penalver
 */
public class ClearPartyTableAction implements Action {
	
	private MainWindow mainWindow;
	
	public ClearPartyTableAction(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	/**
	 * 
	 * @param object
	 * @throws AppException
	 */
	public Object execute(Object object) throws AppException {
		mainWindow.getDataTile().clearPartyTable();
		return null;
	}	
	
}
