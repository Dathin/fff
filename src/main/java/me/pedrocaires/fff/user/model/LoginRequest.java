package me.pedrocaires.fff.user.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class LoginRequest {

	@NotNull
	@Length(min = 3, max = 320)
	private String identifier;

	@NotNull
	@Length(min = 10, max = 50)
	private String password;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
