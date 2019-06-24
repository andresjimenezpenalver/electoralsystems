package ajp.electoralsystems.core.exception;

/**
 * @author Andres Jimenez Penalver
 */
public class AppException extends RuntimeException {

	private static final long serialVersionUID = 2094718617486376935L;

	public AppException() {
		super();
	}

	public AppException(String message) {
		super(message);
	}
	
	public AppException(Exception exception) {
		super(exception);
	}

	public AppException(String message, Exception exception) {
		super(message, exception);
	}

}
