package me.pedrocaires.fff.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    private final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException ex){
        return buildDefaultExceptionResponseEntity(ex, ex.getMessage(), ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        return buildDefaultExceptionResponseEntity(ex, "Something unusual happened, please try again", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ExceptionResponse> buildDefaultExceptionResponseEntity(Exception ex, String message, HttpStatus httpStatus){
        logger.error("Controller advice caught", ex);
        return ResponseEntity.status(httpStatus).body(new ExceptionResponse(message));
    }

}
