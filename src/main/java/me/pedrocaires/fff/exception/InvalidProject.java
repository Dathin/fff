package me.pedrocaires.fff.exception;

import org.springframework.http.HttpStatus;

public class InvalidProject extends CustomException {
    public InvalidProject() {
        super("The project must be from an account you belong to", HttpStatus.BAD_REQUEST);
    }
}
