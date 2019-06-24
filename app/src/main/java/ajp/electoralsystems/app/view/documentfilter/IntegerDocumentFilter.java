package ajp.electoralsystems.app.view.documentfilter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 * @author Andres Jimenez Penalver
 */
public class IntegerDocumentFilter extends DocumentFilter {
	
	/** Current value */
	int currentValue = 0;
	
	/** Max digit number */
	int maxDigitNumber = 0;
	
	/**
	 * Constructor
	 * 
	 * @param maxDigitNumber
	 */
	public IntegerDocumentFilter(int maxDigitNumber) {
		this.maxDigitNumber = maxDigitNumber;
	}
	
	/**
	 * 
	 */
	public void insertString(DocumentFilter.FilterBypass fb, int offset,
			String string, AttributeSet attr) throws BadLocationException {
		if (string == null) {
			return;
		} else {
			replace(fb, offset, 0, string, attr);
		}
	}
	
	/**
	 * 
	 */
	public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
			throws BadLocationException {
		replace(fb, offset, length, "", null);
	}
	
	/**
	 * 
	 */
	public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
			String text, AttributeSet attrs) throws BadLocationException {
		Document doc = fb.getDocument();
		int currentLength = doc.getLength();
		String currentContent = doc.getText(0, currentLength);
		String before = currentContent.substring(0, offset);
		String after = currentContent.substring(length + offset, currentLength);
		String newValue = before + (text == null ? "" : text) + after;
		int temp = checkInput(newValue, offset);
		if (check(temp)) {
			currentValue = temp;
			fb.replace(offset, length, text, attrs);
		}		
	}
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	private boolean check(int number) {
		return (number < Math.pow(10, maxDigitNumber));
	}
	
	/**
	 * 
	 * @param proposedValue
	 * @param offset
	 * @return
	 * @throws BadLocationException
	 */
	private int checkInput(String proposedValue, int offset)
			throws BadLocationException {
		int newValue = 0;
		if (proposedValue.length() > 0) {
			try {
				newValue = Integer.parseInt(proposedValue);				
			} catch (NumberFormatException e) {
				throw new BadLocationException(proposedValue, offset);
			}
		}
		return newValue;
	}
	
}