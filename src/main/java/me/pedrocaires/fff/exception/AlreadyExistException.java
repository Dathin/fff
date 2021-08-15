package me.pedrocaires.fff.exception;

import me.pedrocaires.fff.exception.CustomException;
import org.springframework.http.HttpStatus;

public abstract class AlreadyExistException extends CustomException {

	protected AlreadyExistException(String fields) {
		super(String.format("%s already taken. Please choose different values.", fields), HttpStatus.BAD_REQUEST);
	}

}
