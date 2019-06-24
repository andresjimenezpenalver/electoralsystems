package ajp.electoralsystems.app.view;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

/**
 * @author Andres Jimenez Penalver
 */
public class PartyTableCellEditor extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = -4786713037058843539L;
	JTextField component = null;
	
	public PartyTableCellEditor(DocumentFilter documentFilter) {
		component = new JTextField();
		((AbstractDocument) component.getDocument()).setDocumentFilter(documentFilter);
	}
    
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
		boolean isSelected, int rowIndex, int colIndex) {		
		if (isSelected) {
			// cell (and perhaps other cells) are selected
		}

		if (value == null) {
			((JTextField)component).setText("");
		} else {
			((JTextField)component).setText(value.toString());	
		}						
		return component;
	}

	@Override
	public Object getCellEditorValue() {
		return component.getText();
	}
	
	@Override
	public boolean isCellEditable(EventObject evt) {
		if (evt instanceof MouseEvent) {
			int clickCount = 2;
			return ((MouseEvent)evt).getClickCount() >= clickCount;
		}
		return true;
	}
    
	@Override
	public boolean stopCellEditing() {
		//String s = (String) getCellEditorValue();
		
		//if (!isValid(s)) {
			// Should display an error message at this point
			//return false;
		//}
		return super.stopCellEditing();
	}
	
}	
			

