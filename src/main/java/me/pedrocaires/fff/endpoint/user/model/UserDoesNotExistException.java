package me.pedrocaires.fff.endpoint.user.model;

import org.springframework.http.HttpStatus;

import me.pedrocaires.fff.exception.CustomException;

public class UserDoesNotExistException extends CustomException {

	public UserDoesNotExistException() {
		super("Name and password does not exist. Please check your credentials.", HttpStatus.BAD_REQUEST);
	}

}
