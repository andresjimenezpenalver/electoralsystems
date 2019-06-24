package ajp.electoralsystems.app.view;

import ajp.electoralsystems.core.exception.AppException;

/**
 * @author Andres Jimenez Penalver
 */
public interface UIListener {

	public void handleEvent(String event) throws AppException;

}
