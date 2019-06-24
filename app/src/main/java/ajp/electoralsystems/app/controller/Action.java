package ajp.electoralsystems.app.controller;

import ajp.electoralsystems.core.exception.AppException;

/**
 * @author Andres Jimenez Penalver
 */
public interface Action {

	Object execute(Object object) throws AppException;
	
}
