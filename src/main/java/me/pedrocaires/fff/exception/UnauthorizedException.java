package me.pedrocaires.fff.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CustomException{


    public UnauthorizedException() {
        super("Unauthorized, please check your Authorization header.", HttpStatus.BAD_REQUEST);
    }
}
