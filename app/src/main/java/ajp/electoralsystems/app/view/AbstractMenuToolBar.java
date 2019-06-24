package ajp.electoralsystems.app.view;

import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;

import org.springframework.core.io.ClassPathResource;

import ajp.electoralsystems.core.exception.AppException;

/**
 * @author Andres Jimenez Penalver
 */
public class AbstractMenuToolBar {

	protected static final int MENU_FILE_MNEMONIC = KeyEvent.VK_A;
	protected static final int MENU_HELP_MNEMONIC = KeyEvent.VK_U;
	protected static final int ACTION_ABOUT_MNEMONIC = KeyEvent.VK_D;
	protected static final int ACTION_CALCULATE_MNEMONIC = KeyEvent.VK_C;
	protected static final int ACTION_EXIT_MNEMONIC = KeyEvent.VK_S;
	protected static final int ACTION_NEW_MNEMONIC = KeyEvent.VK_N;
	protected static final int ACTION_OPEN_MNEMONIC = KeyEvent.VK_B;
	protected static final int ACTION_SAVE_MNEMONIC = KeyEvent.VK_G;
	
	
	public AbstractMenuToolBar() {
		super();
	}

	protected ImageIcon getImageIcon(String resource) {
		ImageIcon icon=null;
		try {
			icon = new ImageIcon(new ClassPathResource(resource).getURL());
		} catch (IOException e) {
			throw new AppException(e);
		}
		return icon;
	}

}