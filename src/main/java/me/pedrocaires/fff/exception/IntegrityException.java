package me.pedrocaires.fff.exception;

import org.springframework.http.HttpStatus;

public abstract class IntegrityException extends CustomException {

	protected IntegrityException(String entity, String relation) {
		super(String.format("The %s must be from an %s you belong to", entity, relation), HttpStatus.BAD_REQUEST);
	}

}
