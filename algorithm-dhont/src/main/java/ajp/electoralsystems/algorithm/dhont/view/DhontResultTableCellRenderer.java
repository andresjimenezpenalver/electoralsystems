package ajp.electoralsystems.algorithm.dhont.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import ajp.electoralsystems.algorithm.dhont.model.DhontAlgorithmResult;
import ajp.electoralsystems.algorithm.dhont.model.DhontResultTableModel;

/**
 * @author Andres Jimenez Penalver
 */
public class DhontResultTableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -8837232217355921791L;	
	private DhontAlgorithmResult dhontAlgorithmResult = null;

	public DhontResultTableCellRenderer(DhontAlgorithmResult dhontAlgorithmResult) {
		this.dhontAlgorithmResult = dhontAlgorithmResult;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JLabel label = new JLabel();
		if ((column > DhontResultTableModel.SEPARATOR_COLUMN_INDEX) && (dhontAlgorithmResult.getWinnerAssignmentTable()[row][column - (DhontResultTableModel.SEPARATOR_COLUMN_INDEX+1)])) {
			label.setForeground(Color.GRAY);
		} else {
			label.setForeground(Color.BLACK);
		}
		if (column == 0) {
			label.setHorizontalAlignment(JLabel.LEFT);
		} else {
			label.setHorizontalAlignment(JLabel.RIGHT);
		}
		label.setFont(table.getFont());
		label.setText(value.toString());
		return label;
	}

}
