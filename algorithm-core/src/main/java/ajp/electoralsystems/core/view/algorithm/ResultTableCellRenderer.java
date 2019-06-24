package ajp.electoralsystems.core.view.algorithm;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import ajp.electoralsystems.core.model.algorithm.AlgorithmResult;

/**
 * @author Andres Jimenez Penalver
 */
public class ResultTableCellRenderer extends DefaultTableCellRenderer {

	private AlgorithmResult algorithmResult = null;

	public ResultTableCellRenderer(AlgorithmResult algorithmResult) {
		this.algorithmResult = algorithmResult;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JLabel label = new JLabel();
		label.setForeground(Color.BLACK);
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
