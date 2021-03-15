package simpleSpringHTTPService.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	//Specific Exceptions
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> handleRecordNotFoundException (RecordNotFoundException exception, WebRequest request) {
		
		ErrorDetail errorDetails = new ErrorDetail(new Date(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	//Global Exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException (Exception exception, WebRequest request) {
		
		ErrorDetail errorDetails = new ErrorDetail(new Date(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Specific Exceptions
		@ExceptionHandler(APIException.class)
		public ResponseEntity<?> handleAPIException (APIException exception, WebRequest request) {
			
			ErrorDetail errorDetails = new ErrorDetail(new Date(), exception.getMessage(), request.getDescription(false));
			
			return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
		}
}
