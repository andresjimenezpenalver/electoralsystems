package ajp.electoralsystems.app.view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ajp.electoralsystems.i18n.LocaleChangeListener;
import ajp.electoralsystems.i18n.Messages;
import lombok.Getter;

/**
 * @author Andres Jimenez Penalver
 */
public class TileResult implements LocaleChangeListener  {

	private @Getter JPanel panel;
	private @Getter JTabbedPane tabbedPane;
	
	public TileResult() {	
		create();	
	}	
	
	public void onLocaleChanged(String lang) {
		panel.setBorder(BorderFactory.createTitledBorder(Messages.getString("District.Outcome", lang)));		
		tabbedPane.setTitleAt(0, Messages.getString("Algorithm.Comparator", lang));
	}
	
	public void clear() {	
		tabbedPane.removeAll();
		tabbedPane.setVisible(false);
		panel.setVisible(false);
	}

	private void create() {		
		tabbedPane = new JTabbedPane();
		tabbedPane.setVisible(true);
				
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder(Messages.getString("District.Outcome")));
		panel.setVisible(false);
		panel.add(tabbedPane);		
	}
	
}
