package ajp.electoralsystems.app.controller;

import java.util.Enumeration;

import javax.swing.AbstractButton;

import ajp.electoralsystems.app.view.MainWindow;
import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.i18n.LocaleResolver;

/**
 * @author Andres Jimenez Penalver
 */
public class LanguageAction implements Action {

	private MainWindow mainWindow;
	
	public LanguageAction(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	/**
	 * 
	 */
	public Object execute(Object object) throws AppException {
		String lang=null;
		Enumeration<AbstractButton> eltos = mainWindow.getAppMenuBar().getLanguageButtonGroup().getElements();
		while (eltos.hasMoreElements()) {
			AbstractButton button = eltos.nextElement();
			if (button.isSelected()) {
				lang = button.getName();
			}
		}
		
		LocaleResolver.setLanguage(lang);
		mainWindow.changeLanguage(lang);
		
		return null;
	}
	
}
