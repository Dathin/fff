package me.pedrocaires.fff.exception.alreadyexist;

public class UserAlreadyExistException extends AlreadyExistException {

	public UserAlreadyExistException() {
		super("Account and name");
	}

}
