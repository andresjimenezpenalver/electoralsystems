package ajp.electoralsystems.app.controller;

import ajp.electoralsystems.algorithm.engine.AlgorithmEngine;
import ajp.electoralsystems.app.view.MainWindow;
import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.algorithm.AlgorithmConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public class ShowConfigAction implements Action {
	
	private MainWindow mainWindow;
	private @Getter @Setter AlgorithmEngine algorithmEngine;
	
	public ShowConfigAction(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	/**
	 * 
	 * @param object
	 * @throws AppException
	 */
	public Object execute(Object object) throws AppException {
		AlgorithmConfig config = (AlgorithmConfig) object;
		if (config == null) {
			config = algorithmEngine.getConfig();
		}
		
		mainWindow.getConfigTile().show(config, algorithmEngine.getAlgorithms());
		return null;
	}

	
	
}
