package me.pedrocaires.fff.endpoint.project.models;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CreateProjectRequest {

	@NotNull
	@Length(min = 3, max = 50)
	private String name;

	@NotNull
	@Positive
	private Integer accountId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

}
