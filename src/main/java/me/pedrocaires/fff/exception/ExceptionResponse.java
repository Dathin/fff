package me.pedrocaires.fff.exception;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {

	private final String message;

	public ExceptionResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
