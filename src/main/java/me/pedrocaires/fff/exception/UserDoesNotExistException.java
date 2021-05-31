package me.pedrocaires.fff.exception;

import org.springframework.http.HttpStatus;

public class UserDoesNotExistException extends CustomException{


    public UserDoesNotExistException() {
        super("Account, name and password does not exist. Please check your credentials.", HttpStatus.BAD_REQUEST);
    }
}
