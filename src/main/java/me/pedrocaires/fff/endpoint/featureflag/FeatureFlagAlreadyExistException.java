package me.pedrocaires.fff.endpoint.featureflag;

import me.pedrocaires.fff.exception.AlreadyExistException;

public class FeatureFlagAlreadyExistException extends AlreadyExistException {

	public FeatureFlagAlreadyExistException() {
		super("Name");
	}

}
