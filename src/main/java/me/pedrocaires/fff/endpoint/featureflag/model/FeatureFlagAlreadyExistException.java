package me.pedrocaires.fff.endpoint.featureflag.model;

import me.pedrocaires.fff.exception.AlreadyExistException;

public class FeatureFlagAlreadyExistException extends AlreadyExistException {

	public FeatureFlagAlreadyExistException() {
		super("Name");
	}

}
