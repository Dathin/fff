package me.pedrocaires.fff.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException ex){
        return buildDefaultExceptionResponseEntity(ex, ex.getMessage(), ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex){
        return buildDefaultExceptionResponseEntity(ex, "Something unusual happened, please try again", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ExceptionResponse> buildDefaultExceptionResponseEntity(Exception ex, String message, HttpStatus httpStatus){
        ex.printStackTrace();
        return ResponseEntity.status(httpStatus).body(new ExceptionResponse(message));
    }

}
