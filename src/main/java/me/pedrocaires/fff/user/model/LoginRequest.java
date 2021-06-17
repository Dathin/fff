package me.pedrocaires.fff.user.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class LoginRequest {

	@NotNull
	@Positive
	private Integer accountId;

	@NotNull
	@Length(min = 3, max = 50)
	private String name;

	@NotNull
	@Length(min = 10, max = 50)
	private String password;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
