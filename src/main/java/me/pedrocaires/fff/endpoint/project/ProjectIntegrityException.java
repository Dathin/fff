package me.pedrocaires.fff.endpoint.project;

import me.pedrocaires.fff.exception.IntegrityException;

public class ProjectIntegrityException extends IntegrityException {

	public ProjectIntegrityException() {
		super("Project name");
	}

}
