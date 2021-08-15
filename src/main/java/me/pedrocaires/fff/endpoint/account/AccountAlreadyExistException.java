package me.pedrocaires.fff.endpoint.account;

import me.pedrocaires.fff.exception.AlreadyExistException;

public class AccountAlreadyExistException extends AlreadyExistException {

	public AccountAlreadyExistException() {
		super("Account name");
	}

}
