package ajp.electoralsystems.app.view;

import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSlider;

import ajp.electoralsystems.core.model.algorithm.Algorithm;
import ajp.electoralsystems.core.model.algorithm.AlgorithmConfig;
import ajp.electoralsystems.i18n.LocaleChangeListener;
import ajp.electoralsystems.i18n.Messages;
import lombok.Getter;

/**
 * @author Andres Jimenez Penalver
 */
public class TileConfig implements LocaleChangeListener {

	private @Getter JPanel panel;
	private JPanel phantomPartiesPanel;
	private JPanel thresholdPanel;
	private JPanel algorithmSelectorPanel;
	private @Getter JCheckBox jcbAbstention;
	private @Getter JCheckBox jcbBlankVotes;
	private @Getter JCheckBox jcbInvalidVotes;
	private @Getter JSlider jsThreshold;
	private Map<Algorithm, JCheckBox> algorithmEnabledMap;
	
	public TileConfig() {	
		create();	
	}	
		
	public void clear() {		
		jcbAbstention.setSelected(false);
		jcbBlankVotes.setSelected(false);
		jcbInvalidVotes.setSelected(false);
		jsThreshold.setValue(3);
		
		for (Algorithm algorithm : algorithmEnabledMap.keySet()) {
			algorithmEnabledMap.get(algorithm).setSelected(true);
		}					
	}
	
	public void show(AlgorithmConfig config, List<Algorithm> algorithms) {		
		jcbAbstention.setSelected(config.isAbstentionVotesAsParty());
		jcbBlankVotes.setSelected(config.isBlankVotesAsParty());
		jcbInvalidVotes.setSelected(config.isInvalidVotesAsParty());
		jsThreshold.setValue(config.getVoteThreshold());
		
		for (Algorithm algorithm : algorithms) {
			algorithmEnabledMap.get(algorithm).setSelected(algorithm.isEnabled());
		}					
	}
	
	public void onLocaleChanged(String lang) {
		panel.setBorder(BorderFactory.createTitledBorder(Messages.getString("Configuration", lang)));
		thresholdPanel.setBorder(BorderFactory.createTitledBorder(Messages.getString("VoteThreshold", lang)));
		phantomPartiesPanel.setBorder(BorderFactory.createTitledBorder(Messages.getString("PhantomParties", lang)));		
		jcbAbstention.setText(Messages.getString("AbstentionAsParty", lang));
		jcbBlankVotes.setText(Messages.getString("BlankVotesAsParty", lang));
		jcbInvalidVotes.setText(Messages.getString("InvalidVotesAsParty", lang));
		algorithmSelectorPanel.setBorder(BorderFactory.createTitledBorder(Messages.getString("Algorithms", lang)));		
	}
	
	private void create() {
		thresholdPanel = new JPanel();
		thresholdPanel.setLayout(new BoxLayout(thresholdPanel, BoxLayout.Y_AXIS));
		thresholdPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		thresholdPanel.setBorder(BorderFactory.createTitledBorder(Messages.getString("VoteThreshold")));
		thresholdPanel.setVisible(true);		
		jsThreshold = new JSlider(0, 5, 3);
		jsThreshold.setMajorTickSpacing(1);
		jsThreshold.setMinorTickSpacing(1);
		jsThreshold.setPaintTicks(true);
		jsThreshold.setPaintLabels(true);
		thresholdPanel.add(jsThreshold);

		phantomPartiesPanel = new JPanel();
		phantomPartiesPanel.setLayout(new BoxLayout(phantomPartiesPanel, BoxLayout.Y_AXIS));
		phantomPartiesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		phantomPartiesPanel.setBorder(BorderFactory.createTitledBorder(Messages.getString("PhantomParties")));
		phantomPartiesPanel.setVisible(true);
		jcbAbstention = new JCheckBox(Messages.getString("AbstentionAsParty"));
		jcbBlankVotes = new JCheckBox(Messages.getString("BlankVotesAsParty"));
		jcbInvalidVotes = new JCheckBox(Messages.getString("InvalidVotesAsParty"));
		phantomPartiesPanel.add(jcbAbstention);		
		phantomPartiesPanel.add(jcbBlankVotes);
		phantomPartiesPanel.add(jcbInvalidVotes);
		
		algorithmSelectorPanel = new JPanel();
		algorithmSelectorPanel.setLayout(new BoxLayout(algorithmSelectorPanel, BoxLayout.Y_AXIS));
		algorithmSelectorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		algorithmSelectorPanel.setBorder(BorderFactory.createTitledBorder(Messages.getString("Algorithms")));
		algorithmSelectorPanel.setVisible(false);
				
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(thresholdPanel);
		panel.add(phantomPartiesPanel);
		panel.add(algorithmSelectorPanel);
		panel.setBorder(BorderFactory.createTitledBorder(Messages.getString("Configuration")));		
	}
		
	public void addAlgorithms(List<Algorithm> algorithms) {
		if (algorithms != null) {
			algorithmEnabledMap = new HashMap<>();
			for (Algorithm algorithm : algorithms) {
				JCheckBox ch = new JCheckBox(algorithm.getName()+":"+algorithm.getClass().getSimpleName(), true);	
				algorithmSelectorPanel.add(ch);
				algorithmEnabledMap.put(algorithm, ch);
			}			
		}
		algorithmSelectorPanel.setVisible(true);
	}	
	
	public void toggleVisible() {
		panel.setVisible(!panel.isVisible());
	}
	
	public Map<Algorithm, JCheckBox> getAlgorithmEnabledMap() {
		return algorithmEnabledMap;
	}
	
}
