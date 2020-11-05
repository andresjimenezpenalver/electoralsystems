package ajp.electoralsystems.algorithm.highestaverage.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import ajp.electoralsystems.algorithm.highestaverage.model.HighestAverageAlgorithmResult;
import ajp.electoralsystems.algorithm.highestaverage.model.HighestAverageResultTableModel;

/**
 * @author Andres Jimenez Penalver
 */
public class HighestAverageResultTableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -8837232217355921791L;	
	private HighestAverageAlgorithmResult dhontAlgorithmResult = null;

	public HighestAverageResultTableCellRenderer(HighestAverageAlgorithmResult dhontAlgorithmResult) {
		this.dhontAlgorithmResult = dhontAlgorithmResult;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {		
		Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if ((column > HighestAverageResultTableModel.SEPARATOR_COLUMN_INDEX) 
			&& (dhontAlgorithmResult.getWinnerAssignmentTable()[row][column - (HighestAverageResultTableModel.SEPARATOR_COLUMN_INDEX+1)])) {
			cellComponent.setForeground(Color.GRAY);
			cellComponent.setBackground(Color.GREEN);
			
		} else {
			cellComponent.setForeground(Color.BLACK);
			cellComponent.setBackground(Color.WHITE);
		}
		return cellComponent;
	}

}
