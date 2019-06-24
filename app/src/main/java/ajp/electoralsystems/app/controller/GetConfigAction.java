package ajp.electoralsystems.app.controller;

import ajp.electoralsystems.app.view.MainWindow;
import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.algorithm.AlgorithmConfig;

/**
 * @author Andres Jimenez Penalver
 */
public class GetConfigAction implements Action {
	
	private MainWindow mainWindow;
	
	public GetConfigAction(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
 	/**
	 * Returns the config of the GUI
	 * 
	 * @param object
	 * @return a AlgorithmConfig object
	 * @throws AppException
	 */
	public Object execute(Object object) throws AppException {		
		AlgorithmConfig config = new AlgorithmConfig();
		config.setAbstentionVotesAsParty(mainWindow.getConfigTile().getJcbAbstention().isSelected());
		config.setBlankVotesAsParty(mainWindow.getConfigTile().getJcbBlankVotes().isSelected());
		config.setInvalidVotesAsParty(mainWindow.getConfigTile().getJcbInvalidVotes().isSelected());
		config.setVoteThreshold(mainWindow.getConfigTile().getJsThreshold().getValue());
		return config;		
	}
	
}
