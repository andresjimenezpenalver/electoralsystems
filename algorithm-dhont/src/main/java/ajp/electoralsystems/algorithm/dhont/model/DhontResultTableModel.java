package ajp.electoralsystems.algorithm.dhont.model;

import javax.swing.table.TableColumnModel;

import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.algorithm.DefaultResultTableModel;
import ajp.electoralsystems.i18n.Messages;

/**
 * @author Andres Jimenez Penalver
 */
public class DhontResultTableModel extends DefaultResultTableModel {
	
	private static final long serialVersionUID = 3295993841383756196L;
	//public static int SEPARATOR_COLUMN_INDEX=6;
	public static int SEPARATOR_COLUMN_INDEX=0;
	
	private DhontAlgorithmResult dhontAlgorithmResult;
	
	public DhontResultTableModel(DhontAlgorithmResult dhontAlgorithmResult) {
		super(dhontAlgorithmResult);
		this.dhontAlgorithmResult=dhontAlgorithmResult;
	}		

	public static void onLocaleChanged(String lang, TableColumnModel tcm) {
		tcm.getColumn(0).setHeaderValue(Messages.getString("Party",lang));
	}
	
	@Override
	public Class<?> getColumnClass(int col) {
		if (col <= SEPARATOR_COLUMN_INDEX) {
			return super.getColumnClass(col);
			
		} else {
			return Number.class;
		}	
	}
		
	@Override
	public int getColumnCount() {
		District district = dhontAlgorithmResult.getDistrict();
		if (district == null) {
			return 0;				
		} else {
			return SEPARATOR_COLUMN_INDEX+1+district.getSeats().intValue();	
		}			
	}
	
	@Override
	public String getColumnName(int col) {
		if (col <= SEPARATOR_COLUMN_INDEX) {
			return super.getColumnName(col);
			
		} else {
			return String.valueOf((col-(SEPARATOR_COLUMN_INDEX+1))+1);
		}
	}
	
	@Override
	public int getRowCount() {
		District district = dhontAlgorithmResult.getDistrict();
		if (district == null) {
			return 0;				
		} else {
			return district.getNumberOfParties().intValue();
		}
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		District district = dhontAlgorithmResult.getDistrict();
		if (district == null) {
			return null;	
			
		} else if (col <= SEPARATOR_COLUMN_INDEX) {
			return super.getValueAt(row, col);
			
		} else {
			return dhontAlgorithmResult.getAssignmentTable()[row][col-(SEPARATOR_COLUMN_INDEX+1)];
		}
	}
	
}

