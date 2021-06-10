package me.pedrocaires.fff.exception;

import org.springframework.http.HttpStatus;

public class FeatureFlagDoesNotExistException extends CustomException {

	public FeatureFlagDoesNotExistException() {
		super("Feature flag does not exist. Please check your form.", HttpStatus.BAD_REQUEST);
	}

}
