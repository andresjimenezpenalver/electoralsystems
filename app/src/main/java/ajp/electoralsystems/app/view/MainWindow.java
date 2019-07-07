package ajp.electoralsystems.app.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import ajp.electoralsystems.core.model.algorithm.Algorithm;
import ajp.electoralsystems.i18n.LocaleChangeListener;
import ajp.electoralsystems.i18n.Messages;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Andres Jimenez Penalver
 */
@Slf4j
public class MainWindow extends JFrame {

	private static final long serialVersionUID = -5355391783987421520L;
	public static int WIDTH = 1920;
	public static int HEIGHT = 1200;

	private Container container;
	private @Getter @Setter AppMenuBar appMenuBar;
	private @Getter @Setter AppToolBar appToolBar;
	private @Getter TileConfig configTile;
	private @Getter TileData dataTile;
	private @Getter TileResult resultTile;		
	private List<UIListener> listeners = new ArrayList<>();
	private List<LocaleChangeListener> localeChangeListeners = new ArrayList<LocaleChangeListener>();
		
	/**
	 * Constructor
	 */
	public MainWindow() {		
		appMenuBar = new AppMenuBar();
		addLocaleChangeListener(appMenuBar);
		
		appToolBar = new AppToolBar();
		addLocaleChangeListener(appToolBar);

		JMenuBar jmenuBar = appMenuBar.getJMenuBar();
		JToolBar jtoolBar = appToolBar.getToolBar();
		
		configTile = new TileConfig();
		addLocaleChangeListener(configTile);
		JPanel configTilePanel = configTile.getPanel();
		
		dataTile = new TileData();
		addLocaleChangeListener(dataTile);
		JPanel dataTilePanel = dataTile.getPanel();
		
		resultTile = new TileResult();
		addLocaleChangeListener(resultTile);		
		JPanel resultTilePanel = resultTile.getPanel();
		
		container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add(jtoolBar, BorderLayout.NORTH);
		container.add(configTilePanel, BorderLayout.WEST);
		
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		
		center.add(dataTilePanel, BorderLayout.WEST);
		center.add(resultTilePanel, BorderLayout.CENTER);
		container.add(center, BorderLayout.CENTER);
		
		bindingEvents();		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				fireEvent("exit");
			}
		});
		setJMenuBar(jmenuBar);
		
		setResizable(true);
		setSize(WIDTH, HEIGHT);
		setTitle(Messages.getString("Title"));
		setVisible(true);

		//TODO
		final TileData.FocusHandler policyA = dataTile.new FocusHandler();
		setFocusTraversalPolicy(policyA);
		centerWindow(this);
	}
	
	public void addListener(UIListener listener) {
		listeners.add(listener);
	}

	public void addLocaleChangeListener(LocaleChangeListener listener) {
		localeChangeListeners.add(listener);
	}
	
	public void changeLanguage(String lang) {
		setTitle(Messages.getString("Title", lang));
		
		for (LocaleChangeListener listener : localeChangeListeners) {
    		listener.onLocaleChanged(lang);
    	}
	}
	
	private void bindingEvents() {
		log.debug("bindingEvents");
		getAppMenuBar().getMenuItemNew().addActionListener(e -> {
			fireEvent("clear");
		});
		
		getAppMenuBar().getMenuItemOpen().addActionListener(e -> {
			fireEvent("open");				
		});
		
		getAppMenuBar().getMenuItemSave().addActionListener(e -> {
			fireEvent("save");
		});		
		
		getAppMenuBar().getMenuItemExit().addActionListener(e -> {
			fireEvent("exit");
		});
		
		getAppMenuBar().getMenuItemViewConfig().addActionListener(e -> {
			fireEvent("configToogleView");
		});		
		
		getAppMenuBar().getMenuItemViewLanguageEnglish().addActionListener(e -> {
			fireEvent("languageEnglish");
		});
		
		getAppMenuBar().getMenuItemViewLanguageSpanish().addActionListener(e -> {
			fireEvent("languageSpanish");
		});

		getAppMenuBar().getMenuItemAbout().addActionListener(e -> {
			fireEvent("about");
		});
		
		getAppToolBar().getBtnNew().addActionListener(e -> {	
			fireEvent("clear");
		}); 
		
		getAppToolBar().getBtnOpen().addActionListener(e -> {
			fireEvent("open");				
		}); 
		
		getAppToolBar().getBtnSave().addActionListener(e -> {
			fireEvent("save");
		});		
		
		getAppToolBar().getBtnCalculate().addActionListener(e -> {
			fireEvent("calculate");
		});
		
		getConfigTile().getJcbAbstention().addActionListener(e -> {
			fireEvent("configToggleAbstention");
		});		
		getConfigTile().getJcbBlankVotes().addActionListener(e -> {
			fireEvent("configToggleBlankVotes");
		});	
		getConfigTile().getJcbInvalidVotes().addActionListener(e -> {
			fireEvent("configToggleInvalidVotes");
		});		
		
		getConfigTile().getJsThreshold().addChangeListener(e -> {
			fireEvent("configChangedVoteThreshold");
		});
	}
	
	public void bindingEventsPost() {
		Set<Algorithm> set = getConfigTile().getAlgorithmEnabledMap().keySet();
		for (Algorithm algorithm : set) {
			getConfigTile().getAlgorithmEnabledMap().get(algorithm).addActionListener(e -> {
				fireEvent("configToggleAlgorithmEnabled");
			});
		}
	}
	
    private void centerWindow(Window window) {
    	//window.setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = window.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        window.setLocation(
            (screenSize.width - frameSize.width) / 2,
            (screenSize.height - frameSize.height) / 2);
    }
    
    private void fireEvent(String event) {		
    	log.debug("fireEvent " + event);		
    	for (UIListener uiListener : listeners) {
    		log.debug("uiListener=" + uiListener);		
    		
    		uiListener.handleEvent(event);
    	}
    }
	
	public static void main(String args[]) {
		new MainWindow();		
	}

}
