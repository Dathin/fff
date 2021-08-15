package me.pedrocaires.fff.endpoint.user.model;

import me.pedrocaires.fff.exception.AlreadyExistException;

public class UserAlreadyExistException extends AlreadyExistException {

	public UserAlreadyExistException() {
		super("Identifier");
	}

}
