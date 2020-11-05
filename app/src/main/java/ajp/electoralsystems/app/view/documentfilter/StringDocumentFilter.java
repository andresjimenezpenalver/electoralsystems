package ajp.electoralsystems.app.view.documentfilter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 * @author Andres Jimenez Penalver
 */
public class StringDocumentFilter extends DocumentFilter {
	
	/** Max character number in the string */
	int maxCharacterNumber = 0;
	
	/**
	 * Constructor
	 * 
	 * @param maxCharacterNumber
	 */
	public StringDocumentFilter(int maxCharacterNumber) {
		this.maxCharacterNumber = maxCharacterNumber;
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
		checkInput(newValue, offset);
		if (check(newValue)) {
			fb.replace(offset, length, text, attrs);
		}
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	private boolean check(String str) {
		return (str.length() < maxCharacterNumber);
	}
	
	/**
	 * 
	 * @param proposedValue
	 * @param offset
	 * @throws BadLocationException
	 */
	private void checkInput(String proposedValue, int offset)
			throws BadLocationException {
		if (proposedValue.length() > 0) {
			for (int i = 0; i < proposedValue.length(); i++) {
				char character = proposedValue.charAt(i);
				boolean valid = Character.isLetter(character) || '+'==character || '-'==character || '.'==character || ' '==character;
				if (!valid) {
					throw new BadLocationException(proposedValue, offset);
				}				
			}		
		}
	}
	
}