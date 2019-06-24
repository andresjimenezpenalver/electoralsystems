package ajp.electoralsystems.core.model.algorithm;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.Party;
import ajp.electoralsystems.core.model.PartyResult;
import ajp.electoralsystems.i18n.Messages;

/**
 * @author Andres Jimenez Penalver
 */
public class DefaultResultTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 3295993841383756196L;
	private AlgorithmResult algorithmResult = null;

	public DefaultResultTableModel(AlgorithmResult algorithmResult) {
		this.algorithmResult = algorithmResult;
	}
		
	@Override
	public Class<?> getColumnClass(int col) {
		Class<?> columnClass = null;
		switch (col) {
			case 0:
				columnClass = String.class;
			break;
			case 2:
			case 3:
			case 5:
			case 6:
				columnClass = Float.class;
			break;
			case 1:
			case 4:
				columnClass = Integer.class;
			break;
		}
		return columnClass;	
	}
		
	public int getColumnCount() {
		District district = algorithmResult.getDistrict();
		if (district == null) {
			return 0;				
		} else {
			return 7;	
		}			
	}
	
	public static void onLocaleChanged(String lang, TableColumnModel tcm) {
		tcm.getColumn(0).setHeaderValue(Messages.getString("Party",lang));
		tcm.getColumn(1).setHeaderValue(Messages.getString("Votes",lang));
		tcm.getColumn(2).setHeaderValue(Messages.getString("Votes",lang)+ "(%)");
		tcm.getColumn(3).setHeaderValue(Messages.getString("Census",lang)+ "(%)");
		tcm.getColumn(4).setHeaderValue(Messages.getString("Seats",lang));
		tcm.getColumn(5).setHeaderValue(Messages.getString("Seats",lang)+ "(%)");
		tcm.getColumn(6).setHeaderValue(Messages.getString("Seats.Cost",lang)+ "(%)");
	}
	
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return Messages.getString("Party");
		case 1:
			return Messages.getString("Votes");
		case 2:
			return Messages.getString("Votes") + "(%)";
		case 3:
			return Messages.getString("Census") + "(%)";
		case 4:
			return Messages.getString("Seats");
		case 5:
			return Messages.getString("Seats") + "(%)";
		case 6:
			return Messages.getString("Seats.Cost");
		default:
			return "";
		}
	}
	
	public int getRowCount() {
		District district = algorithmResult.getDistrict();
		if (district == null) {
			return 0;				
		} else {
			return district.getNumberOfParties().intValue();
		}
	}
	
	public Object getValueAt(int row, int col) {
		District district = algorithmResult.getDistrict();
		if (district == null) {
			return null;				
		} else {
			Party party = district.getParties()[row];
			PartyResult r = algorithmResult.getResults().getPartyResult(party);			
			Object value = null;
			switch (col) {
				case 0:
					value = party.getName();
				break;
				case 1:
					value = party.getVotes();
				break;
				case 2:
					value = party.getVotePercentage();
				break;
				case 3:
					value = party.getVotePercentageOverCensus();
				break;
				case 4:
					value = r.getSeats();
				break;
				case 5:
					value = r.getSeatPercentage();
				break;
				case 6:
					value = r.getSeatCost();
				break;
			}
			return value;	
		}
	}
	
}

