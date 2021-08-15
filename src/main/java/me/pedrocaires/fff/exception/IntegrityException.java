package me.pedrocaires.fff.exception;

import me.pedrocaires.fff.exception.CustomException;
import org.springframework.http.HttpStatus;

public abstract class IntegrityException extends CustomException {

	protected IntegrityException(String entity) {
		super(String.format("The %s must be from an account you belong to", entity), HttpStatus.BAD_REQUEST);
	}

}
