package ajp.electoralsystems.app.controller;

import java.util.List;

import javax.swing.JSlider;

import ajp.electoralsystems.algorithm.engine.AlgorithmEngine;
import ajp.electoralsystems.app.view.MainWindow;
import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.algorithm.Algorithm;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public class ConfigAction implements Action {
	
	private MainWindow mainWindow;
	private @Getter @Setter AlgorithmEngine algorithmEngine;
	
	public ConfigAction(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	/**
	 * 
	 * @param object
	 * @throws AppException
	 */
	public Object execute(Object object) throws AppException {	
		Object response=null;
		String event = (object == null ? "" : object.toString());
		switch (event) {
			case "configInit":
				mainWindow.getConfigTile().addAlgorithms(algorithmEngine.getAlgorithms());
				mainWindow.bindingEventsPost();				
				break;
			case "configToogleView":
				mainWindow.getConfigTile().toggleVisible();
				break;
			case "configToggleAbstention":
				algorithmEngine.getConfig().toggleAbstentionVotesAsParty();
				break;
			case "configToggleBlankVotes":
				algorithmEngine.getConfig().toggleBlankVotesAsParty();
				break;
			case "configToggleInvalidVotes":
				algorithmEngine.getConfig().toggleInvalidVotesAsParty();
				break;
			case "configToggleAlgorithmEnabled":
				List<Algorithm> algorithms = algorithmEngine.getAlgorithms();
				for (Algorithm algorithm : algorithms) {
					algorithm.setEnabled(mainWindow.getConfigTile().getAlgorithmEnabledMap().get(algorithm).isSelected());
				}				
				break;
			case "configChangedVoteThreshold":
				JSlider source = mainWindow.getConfigTile().getJsThreshold();
				if (!source.getValueIsAdjusting()) {
				    int value = (int) source.getValue();
				    algorithmEngine.getConfig().setVoteThreshold(value);
				}
				break;
		}				
		return response;		
	}
	
}
