package ajp.electoralsystems.core.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * @author Andres Jimenez Penalver
 */
public class NumberUtils {

	private static NumberFormat nf = NumberFormat.getInstance(new Locale("es", "ES"));
	private static NumberFormat nf_enUS = NumberFormat.getInstance(new Locale("en", "US"));
	
	static {
		nf.setMinimumFractionDigits(3);
		nf.setMaximumFractionDigits(3);
	}

	private NumberUtils() {
	}

	public static NumberFormat getNumberFormat() {
		return nf;
	}

	public static double format(double value) throws ParseException {
		String s = nf.format(value);
		Double f = nf.parse(s).doubleValue();
		return f;
	}

}
