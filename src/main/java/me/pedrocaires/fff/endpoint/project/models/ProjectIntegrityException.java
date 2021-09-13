package me.pedrocaires.fff.endpoint.project.models;

import me.pedrocaires.fff.exception.IntegrityException;

public class ProjectIntegrityException extends IntegrityException {

	public ProjectIntegrityException() {
		super("project", "account");
	}

}
