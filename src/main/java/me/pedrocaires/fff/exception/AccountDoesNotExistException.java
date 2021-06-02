package me.pedrocaires.fff.exception;

import org.springframework.http.HttpStatus;

public class AccountDoesNotExistException extends CustomException {

	public AccountDoesNotExistException() {
		super("Must be the first user at account or authenticated user from account. Please check what scenario do you wish.",
				HttpStatus.BAD_REQUEST);
	}

}
