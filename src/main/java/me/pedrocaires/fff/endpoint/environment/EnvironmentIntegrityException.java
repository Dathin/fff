package me.pedrocaires.fff.endpoint.environment;

import me.pedrocaires.fff.exception.IntegrityException;

public class EnvironmentIntegrityException extends IntegrityException {

	public EnvironmentIntegrityException() {
		super("Environment name");
	}

}
