package me.pedrocaires.fff.exception;

import org.springframework.http.HttpStatus;

public class InvalidCreateUserOnAccount extends CustomException{


    public InvalidCreateUserOnAccount() {
        super("Account and name already taken. Please choose different values or delete the old one.", HttpStatus.BAD_REQUEST);
    }
}
