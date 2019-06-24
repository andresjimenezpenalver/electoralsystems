package ajp.electoralsystems.algorithm.dhont.view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ajp.electoralsystems.algorithm.dhont.model.DhontAlgorithmResult;
import ajp.electoralsystems.algorithm.dhont.model.DhontResultTableModel;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.algorithm.AlgorithmResult;
import ajp.electoralsystems.core.view.algorithm.DefaultPanelUI;

/**
 * @author Andres Jimenez Penalver
 */
public class DhontPanelUI extends DefaultPanelUI {
		
	private JTable table;
	
	public JPanel getAlgorithmDetailPanel(District district, AlgorithmResult algorithmResult) {		
		DhontAlgorithmResult dhontAlgorithmResult = (DhontAlgorithmResult) algorithmResult;
		
		DhontResultTableModel tableModel = new DhontResultTableModel(dhontAlgorithmResult);				
		DhontResultTableCellRenderer cellRenderer = new DhontResultTableCellRenderer(dhontAlgorithmResult);	
		table = new JTable(tableModel);
		table.setDefaultRenderer(Number.class, cellRenderer);
		table.setDefaultRenderer(Float.class, cellRenderer);
		table.setDefaultRenderer(String.class, cellRenderer);		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				table.clearSelection();
			}
		});
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
		DhontResultTableModel.onLocaleChanged(lang, table.getColumnModel());
	}	
	
}
