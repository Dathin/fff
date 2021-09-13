package me.pedrocaires.fff.endpoint.featureflag.model;

import org.springframework.http.HttpStatus;

import me.pedrocaires.fff.exception.CustomException;

public class FeatureFlagDoesNotExistException extends CustomException {

	public FeatureFlagDoesNotExistException() {
		super("Feature flag does not exist. Please check your form.", HttpStatus.BAD_REQUEST);
	}

}
