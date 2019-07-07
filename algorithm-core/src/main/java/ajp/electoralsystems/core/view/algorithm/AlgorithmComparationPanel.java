package ajp.electoralsystems.core.view.algorithm;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.DistrictAlgorithmResult;
import ajp.electoralsystems.core.model.Party;
import ajp.electoralsystems.core.model.algorithm.Algorithm;
import ajp.electoralsystems.i18n.LocaleChangeListener;
import ajp.electoralsystems.i18n.Messages;

public class AlgorithmComparationPanel implements LocaleChangeListener {

	private JTable table;
	
	public AlgorithmComparationPanel() {
		
	}
	
	public JPanel createPanel(final District district, final DistrictAlgorithmResult results) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		final Algorithm[] algorithms = new Algorithm[results.getAlgorithmsApplied().size()];
		results.getAlgorithmsApplied().toArray(algorithms);
		
		table = new JTable();
		table.setModel(new TableModel() {			
			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			}
			
			@Override
			public void removeTableModelListener(TableModelListener l) {
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
			
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Party party = district.getParties()[rowIndex];
				Object value;
				switch (columnIndex) {
					case 0:
						value = party.getName();
					break;
					case 1:
						value = party.getVotes();
					break;
					default:						
						Algorithm algorithm = algorithms[columnIndex-2];
						value = results.getAlgorithmResult(algorithm).getResults().getPartyResult(party).getSeats();
					break;
				}
				return value;	
			}
			
			@Override
			public int getRowCount() {
				return district.getNumberOfParties();
			}
			
			@Override
			public String getColumnName(int columnIndex) {
				switch (columnIndex) {
				case 0:
					return Messages.getString("Party");
				case 1:
					return Messages.getString("Votes");				
				default:
					return algorithms[columnIndex-2].getName();
				}
			}
			
			@Override
			public int getColumnCount() {
				return 2+results.getAlgorithmsApplied().size();
			}
			
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;	
			}
			
			@Override
			public void addTableModelListener(TableModelListener l) {				
			}
		});
		
		JScrollPane sp = new JScrollPane(table);
		sp.setVisible(true);		
		panel.add(sp);		
		return panel; 
	}
	
	public void onLocaleChanged(String lang) {
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setHeaderValue(Messages.getString("Party",lang));
		tcm.getColumn(1).setHeaderValue(Messages.getString("Votes",lang));
	}
	
}
