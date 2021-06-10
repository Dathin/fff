package me.pedrocaires.fff.exception;

import org.springframework.http.HttpStatus;

public class InvalidCreateUserOnAccountException extends CustomException {

	public InvalidCreateUserOnAccountException() {
		super("Account and name already taken. Please choose different values or delete the old one.",
				HttpStatus.BAD_REQUEST);
	}

}
