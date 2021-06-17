package me.pedrocaires.fff.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {

	private final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

	protected final String unusualMessage = "Something unusual happened, please try again";

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ExceptionResponse> handleCustomException(CustomException ex) {
		return buildDefaultExceptionResponseEntity(ex, ex.getMessage(), ex.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
		return buildDefaultExceptionResponseEntity(ex, unusualMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<List<FormValidationResponse>> handleValidationExceptions(BindException ex) {
		logger.error("Controller advice caught at form", ex);
		var formValidationsResponse = ex.getBindingResult().getAllErrors().stream().map(objectError -> {
			var fieldError = objectError instanceof FieldError ? ((FieldError) objectError).getField()
					: ex.getObjectName();
			return new FormValidationResponse(fieldError, objectError.getDefaultMessage());
		}).collect(Collectors.toList());
		return ResponseEntity.badRequest().body(formValidationsResponse);
	}

	private ResponseEntity<ExceptionResponse> buildDefaultExceptionResponseEntity(Exception ex, String message,
			HttpStatus httpStatus) {
		logger.error("Controller advice caught", ex);
		return ResponseEntity.status(httpStatus).body(new ExceptionResponse(message));
	}

}
