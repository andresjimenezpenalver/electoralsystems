package ajp.electoralsystems.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Andres Jimenez Penalver
 */
public class Messages {
	
	private static final String BUNDLE_NAME = "ajp.electoralsystems.i18n.messages";

	private Messages() {
	}
	
	public static String getString(String key) {
		try {
			return ResourceBundle.getBundle(BUNDLE_NAME, LocaleResolver.getLocale()).getString(key);
			
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static String getFormattedString(String key, Object...params) {
		try {
			return MessageFormat.format(ResourceBundle.getBundle(BUNDLE_NAME, LocaleResolver.getLocale()).getString(key), params);
			
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static String getString(String key, String language) {
		try {
			return ResourceBundle.getBundle(BUNDLE_NAME, new Locale(language)).getString(key);
			
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static String getFormattedLocalizedString(String key, String language, Object... params  ) {
        try {
            return MessageFormat.format(ResourceBundle.getBundle(BUNDLE_NAME, new Locale(language)).getString(key), params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
	
}
