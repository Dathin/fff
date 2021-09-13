package me.pedrocaires.fff.endpoint.environment.model;

import me.pedrocaires.fff.exception.IntegrityException;

public class EnvironmentIntegrityException extends IntegrityException {

	public EnvironmentIntegrityException() {
		super("environment", "project");
	}

}
