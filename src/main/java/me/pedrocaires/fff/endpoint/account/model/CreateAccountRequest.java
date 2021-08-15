package me.pedrocaires.fff.endpoint.account.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CreateAccountRequest {

	@NotNull
	@Length(min = 3, max = 50)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
