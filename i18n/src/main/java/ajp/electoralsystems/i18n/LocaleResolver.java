package ajp.electoralsystems.i18n;

import java.util.Locale;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public class LocaleResolver {

	private static final Locale LOCALE_ES = new Locale("es");	
 
	private static @Getter @Setter Locale locale = Locale.ENGLISH;
		
	public static void setLanguage(String lang) {
		switch (lang) {
		case "es":
			setLocale(LOCALE_ES);
			break;
		case "en":
			setLocale(Locale.ENGLISH);
			break;
		default:
			throw new IllegalArgumentException("language " + lang + " is not supported");
		}
	}
	
}
