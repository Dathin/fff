package me.pedrocaires.fff.endpoint.user.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class LoginRequest extends IsWebClientRequest{

	@NotNull
	@Length(min = 10, max = 50)
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
