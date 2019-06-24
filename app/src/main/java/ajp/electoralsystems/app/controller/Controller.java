package ajp.electoralsystems.app.controller;

import javax.swing.JOptionPane;

import ajp.electoralsystems.app.view.MainWindow;
import ajp.electoralsystems.app.view.UIListener;
import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.algorithm.AlgorithmConfig;
import ajp.electoralsystems.i18n.Messages;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Andres Jimenez Penalver
 */
@Slf4j
public class Controller implements UIListener {

	private MainWindow mainWindow;	
	private AboutAction aboutAction;
	private ClearPartyTableAction clearPartyTableAction;
	private ClearAction clearAction;
	private ConfigAction configAction;
	private ExitAction exitAction;
	private GetConfigAction getConfigAction;
	private GetDistrictFileAction getDistrictFileAction;
	private GetDistrictAction getDistrictAction;
	private LanguageAction languageAction;
	private SaveDistrictFileAction saveDistrictFileAction;
	private ShowDistrictAction showDistrictAction;
	private ShowConfigAction showConfigAction;
	
	public Controller(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.mainWindow.addListener(this);		
	}
	
	@Override
	public void handleEvent(String event) throws AppException {
		log.info("handleEvent " + event);
		AlgorithmConfig config = null;
		District district = null;
		switch (event) {
		case "configToggleAbstention":
		case "configToggleBlankVotes":
		case "configToggleInvalidVotes":
		case "configToogleView":
		case "configInit":
		case "configToggleAlgorithmEnabled":
		case "configChangedVoteThreshold":			
			getConfigAction().execute(event);
			break;	
		case "languageEnglish":
		case "languageSpanish":
			getLanguageAction().execute(null);
			break;
		case "clear":
			getClearAction().execute(null);
			break;
		case "open":		
			district = (District) getGetDistrictFileAction().execute(null);
			config = (AlgorithmConfig) getGetConfigAction().execute(null);
			getClearAction().execute(null);
			getShowConfigAction().execute(config);
			getShowDistrictAction().execute(district);
			break;
		case "save":
			district = (District) getGetDistrictAction().execute(null);
			getSaveDistrictFileAction().execute(district);
			break;
		case "calculate":
			if (!getConfigAction().getAlgorithmEngine().hasSomeoneAlgorithmEnabled()) {
				JOptionPane.showMessageDialog(null, Messages.getString("Error.NoAlgorithmSelected"), Messages.getString("Error.NoAlgorithmSelected"), JOptionPane.ERROR_MESSAGE);
				
			} else {
				district = (District) getGetDistrictAction().execute(null);
				config = (AlgorithmConfig) getGetConfigAction().execute(null);
				getClearAction().execute(null);
				getShowConfigAction().execute(config);
				getShowDistrictAction().execute(district);
			}
			break;
		case "exit":
			getExitAction().execute(null);			
			break;
		case "about":
			getAboutAction().execute(null);
			break;		
		}
	}	
	
	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public ClearPartyTableAction getClearPartyTableAction() {
		return clearPartyTableAction;
	}

	public void setClearPartyTableAction(ClearPartyTableAction clearPartyTableAction) {
		this.clearPartyTableAction = clearPartyTableAction;
	}

	public ClearAction getClearAction() {
		return clearAction;
	}

	public void setClearAction(ClearAction clearAction) {
		this.clearAction = clearAction;
	}

	public ExitAction getExitAction() {
		return exitAction;
	}

	public void setExitAction(ExitAction exitAction) {
		this.exitAction = exitAction;
	}

	public GetDistrictFileAction getGetDistrictFileAction() {
		return getDistrictFileAction;
	}

	public void setGetDistrictFileAction(GetDistrictFileAction getDistrictFileAction) {
		this.getDistrictFileAction = getDistrictFileAction;
	}

	public GetDistrictAction getGetDistrictAction() {
		return getDistrictAction;
	}

	public void setGetDistrictAction(GetDistrictAction getDistrictAction) {
		this.getDistrictAction = getDistrictAction;
	}

	public SaveDistrictFileAction getSaveDistrictFileAction() {
		return saveDistrictFileAction;
	}

	public void setSaveDistrictFileAction(SaveDistrictFileAction saveDistrictFileAction) {
		this.saveDistrictFileAction = saveDistrictFileAction;
	}

	public ShowDistrictAction getShowDistrictAction() {
		return showDistrictAction;
	}

	public void setShowDistrictAction(ShowDistrictAction showDistrictAction) {
		this.showDistrictAction = showDistrictAction;
	}

	public AboutAction getAboutAction() {
		return aboutAction;
	}

	public void setAboutAction(AboutAction aboutAction) {
		this.aboutAction = aboutAction;
	}

	public ConfigAction getConfigAction() {
		return configAction;
	}

	public void setConfigAction(ConfigAction configAction) {
		this.configAction = configAction;
	}

	public ShowConfigAction getShowConfigAction() {
		return showConfigAction;
	}

	public void setShowConfigAction(ShowConfigAction showConfigAction) {
		this.showConfigAction = showConfigAction;
	}

	public GetConfigAction getGetConfigAction() {
		return getConfigAction;
	}

	public void setGetConfigAction(GetConfigAction getConfigAction) {
		this.getConfigAction = getConfigAction;
	}

	public LanguageAction getLanguageAction() {
		return languageAction;
	}

	public void setLanguageAction(LanguageAction languageAction) {
		this.languageAction = languageAction;
	}
	
}
