package ajp.electoralsystems.algorithm.quota.view;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ajp.electoralsystems.algorithm.quota.model.QuotaAlgorithmResult;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.algorithm.AlgorithmResult;
import ajp.electoralsystems.core.view.algorithm.DefaultPanelUI;
import ajp.electoralsystems.i18n.Messages;

/**
 * @author Andres Jimenez Penalver
 */
public class QuotaPanelUI extends DefaultPanelUI {

	private JLabel lblQuota;
	private JLabel lblQuotaValue;
	
	public JPanel getAlgorithmDetailPanel(District district, AlgorithmResult algorithmResult) {		
		QuotaAlgorithmResult quotaAlgorithmResult = (QuotaAlgorithmResult) algorithmResult;
		
		lblQuota = new JLabel(Messages.getString("Quota")); 
		lblQuota.setFont(lblQuota.getFont().deriveFont(Font.PLAIN));
		
		lblQuotaValue = new JLabel(quotaAlgorithmResult.getQuota().toString()); 
		lblQuotaValue.setFont(lblQuotaValue.getFont().deriveFont(Font.PLAIN));
		
		JPanel quotaPanel = new JPanel();
		quotaPanel.setLayout(new GridLayout(1,2));
		quotaPanel.add(lblQuota);
		quotaPanel.add(lblQuotaValue);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(quotaPanel);
		
		return panel;
	}
	
	public void onLocaleChanged(String lang) {
		super.onLocaleChanged(lang);
	}	
	
}
