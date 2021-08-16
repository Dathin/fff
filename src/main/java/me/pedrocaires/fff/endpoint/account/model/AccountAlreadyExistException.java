package me.pedrocaires.fff.endpoint.account.model;

import me.pedrocaires.fff.exception.AlreadyExistException;

public class AccountAlreadyExistException extends AlreadyExistException {

	public AccountAlreadyExistException() {
		super("Account name");
	}

}
