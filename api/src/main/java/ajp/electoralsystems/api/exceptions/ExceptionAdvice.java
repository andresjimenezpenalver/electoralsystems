package ajp.electoralsystems.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import ajp.electoralsystems.core.exception.NotFoundAlgorithmException;

/**
 * @author Andres Jimenez Penalver
 */
@ControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(INTERNAL_SERVER_ERROR, e);
    }
    
    @ExceptionHandler({NotFoundAlgorithmException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundAlgorithmException e) {
    	ExceptionResponse response = new ExceptionResponse();
    	response.setMessage(e.getLocalizedMessage());
        return error(NOT_FOUND, response);
    }

    private ResponseEntity<Object> error(HttpStatus status, Object o) {
    	return ResponseEntity.status(status).body(o);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
    	return ResponseEntity.status(status).body(e.getMessage());
    }
    
}