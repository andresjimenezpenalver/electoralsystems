package ajp.electoralsystems.core.exception;

import lombok.Getter;

/**
 * @author Andres Jimenez Penalver
 */
public class NotFoundAlgorithmException extends AppException {

	private @Getter String algorithmId;
	
	public NotFoundAlgorithmException(String algorithmId) {
		super("Not found " + algorithmId + " algorithm");
		this.algorithmId=algorithmId;
	}
	
}
