package ajp.electoralsystems.algorithm.highestaverage.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ajp.electoralsystems.algorithm.highestaverage.model.HighestAverageAlgorithmResult;
import ajp.electoralsystems.algorithm.highestaverage.model.HighestAverageResultTableModel;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.algorithm.AlgorithmResult;
import ajp.electoralsystems.core.view.algorithm.DefaultPanelUI;

/**
 * @author Andres Jimenez Penalver
 */
public class HighestAveragePanelUI extends DefaultPanelUI {
		
	private JTable table;
	
	public JPanel getAlgorithmDetailPanel(District district, AlgorithmResult algorithmResult) {		
		HighestAverageAlgorithmResult dhontAlgorithmResult = (HighestAverageAlgorithmResult) algorithmResult;
		
		HighestAverageResultTableModel tableModel = new HighestAverageResultTableModel(dhontAlgorithmResult);				
		HighestAverageResultTableCellRenderer cellRenderer = new HighestAverageResultTableCellRenderer(dhontAlgorithmResult);	
		
		table = new JTable(tableModel);		
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}		
		
		if (tableModel.getColumnCount() <= 13) {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		} else {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		}			
				
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(scrollPane);
		
		return panel;
	}
	
	public void onLocaleChanged(String lang) {
		super.onLocaleChanged(lang);
		HighestAverageResultTableModel.onLocaleChanged(lang, table.getColumnModel());
	}	
	
}
