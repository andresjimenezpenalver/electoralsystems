package ajp.electoralsystems.app.view;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

import ajp.electoralsystems.i18n.LocaleChangeListener;
import ajp.electoralsystems.i18n.Messages;
import lombok.Getter;

/**
 * @author Andres Jimenez Penalver
 */
public class AppMenuBar extends AbstractMenuToolBar implements LocaleChangeListener {

	private @Getter JMenuBar jMenuBar;
	private JMenu menuFile;
	private @Getter JMenuItem menuItemNew;
	private @Getter JMenuItem menuItemOpen;
	private @Getter JMenuItem menuItemSave;
	private JSeparator separator;
	private @Getter JMenuItem menuItemExit;
	
	private JMenu menuView;
	private @Getter JMenuItem menuItemViewConfig;
	private JMenu menuItemViewLanguage;
	private @Getter ButtonGroup languageButtonGroup;
	private @Getter JRadioButtonMenuItem menuItemViewLanguageEnglish;
	private @Getter JRadioButtonMenuItem menuItemViewLanguageSpanish;	
	
	private JMenu menuHelp;
	private @Getter JMenuItem menuItemAbout;	
	
	public AppMenuBar() {
		createMenuBar();
	}
			
	public void onLocaleChanged(String lang) {
		menuFile.setText(Messages.getString("File", lang));
		menuItemNew.setText(Messages.getString("New", lang));
		menuItemOpen.setText(Messages.getString("Open", lang));
		menuItemSave.setText(Messages.getString("Save", lang));
		menuItemExit.setText(Messages.getString("Exit", lang));
		
		menuView.setText(Messages.getString("View", lang));
		menuItemViewConfig.setText(Messages.getString("Configuration", lang));
		menuItemViewLanguage.setText(Messages.getString("Language", lang));
		menuItemViewLanguageEnglish.setText(Messages.getString("Language.English", lang));
		menuItemViewLanguageSpanish.setText(Messages.getString("Language.Spanish", lang));		
		
		menuHelp.setText(Messages.getString("Help", lang));
		menuItemAbout.setText(Messages.getString("About", lang));
	}
	
	private void createMenuBar() {
		separator = new JSeparator();
		
		// File AppMenuBar
		menuItemNew = new JMenuItem(Messages.getString("New"), getImageIcon("ajp/electoralsystems/images/New24.gif")); 
		menuItemNew.setMnemonic(ACTION_NEW_MNEMONIC);
		menuItemOpen = new JMenuItem(Messages.getString("Open"), getImageIcon("ajp/electoralsystems/images/Open24.gif")); 
		menuItemOpen.setMnemonic(ACTION_OPEN_MNEMONIC);
		menuItemSave = new JMenuItem(Messages.getString("Save"), getImageIcon("ajp/electoralsystems/images/Save24.gif")); 
		menuItemSave.setMnemonic(ACTION_SAVE_MNEMONIC);
		menuItemExit = new JMenuItem(Messages.getString("Exit")); 
		menuItemExit.setMnemonic(ACTION_EXIT_MNEMONIC);
		
		menuFile = new JMenu(Messages.getString("File")); 
		menuFile.add(menuItemNew);
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemSave);
		menuFile.add(separator);
		menuFile.add(menuItemExit);
		menuFile.setMnemonic(MENU_FILE_MNEMONIC);
		
		// View AppMenuBar
		menuItemViewConfig = new JCheckBoxMenuItem(Messages.getString("Configuration"), true);
		
		languageButtonGroup = new ButtonGroup();
		menuItemViewLanguage = new JMenu(Messages.getString("Language"));
		menuItemViewLanguageEnglish = new JRadioButtonMenuItem(Messages.getString("Language.English"), true);
		menuItemViewLanguageEnglish.setName("en");
		menuItemViewLanguageSpanish = new JRadioButtonMenuItem(Messages.getString("Language.Spanish"));
		menuItemViewLanguageSpanish.setName("es");
		menuItemViewLanguage.add(menuItemViewLanguageEnglish);
		languageButtonGroup.add(menuItemViewLanguageEnglish);
		menuItemViewLanguage.add(menuItemViewLanguageSpanish);
		languageButtonGroup.add(menuItemViewLanguageSpanish);
		
		menuView = new JMenu(Messages.getString("View"));		 
		menuView.add(menuItemViewConfig);
		menuView.add(menuItemViewLanguage);
		
		//
		menuItemAbout = new JMenuItem(Messages.getString("About"), getImageIcon("ajp/electoralsystems/images/About24.gif"));		 
		menuItemAbout.setMnemonic(ACTION_ABOUT_MNEMONIC);
		
		// Help AppMenuBar
		menuHelp = new JMenu(Messages.getString("Help")); 
		menuHelp.add(menuItemAbout);
		menuHelp.setMnemonic(MENU_HELP_MNEMONIC);
		
		jMenuBar = new JMenuBar();
		jMenuBar.add(menuFile);
		jMenuBar.add(menuView);
		jMenuBar.add(menuHelp);		
	}	

}
