package me.pedrocaires.fff.exception;

import org.springframework.http.HttpStatus;

public abstract class AlreadyExistException extends CustomException {

	protected AlreadyExistException(String fields) {
		super(String.format("%s already taken. Please choose different values.", fields), HttpStatus.BAD_REQUEST);
	}

	protected AlreadyExistException(String message, String fields) {
		super(String.format(message, fields), HttpStatus.BAD_REQUEST);
	}

}
