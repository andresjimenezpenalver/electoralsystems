package ajp.electoralsystems.app.controller;

import ajp.electoralsystems.app.view.MainWindow;
import ajp.electoralsystems.core.exception.AppException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Andres Jimenez Penalver
 */
@Slf4j
public class ClearAction implements Action {
	
	private MainWindow mainWindow;
	
	public ClearAction(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	/**
	 * 
	 * @param object 
	 * @throws AppException
	 */
	public Object execute(Object object) throws AppException {
		mainWindow.getConfigTile().clear();
		mainWindow.getDataTile().clear();
		mainWindow.getResultTile().clear();
		return null;
	}
	
}
