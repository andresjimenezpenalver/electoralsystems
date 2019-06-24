package ajp.electoralsystems.api.exceptions;

/**
 * @author Andres Jimenez Penalver
 */
class ExceptionResponse {
	
	private String code;
	private String error;
	private String message;
	private String detail;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public ExceptionResponse withCode(String code) {
		this.code = code;
		return this;
	}
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public ExceptionResponse withError(String error) {
		this.error = error;
		return this;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ExceptionResponse withMessage(String message) {
		this.message = message;
		return this;
	}
			
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public ExceptionResponse withDetail(String detail) {
		this.detail= detail;
		return this;
	}
	
	
}