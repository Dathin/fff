package me.pedrocaires.fff.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException{

    private final HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
