package me.pedrocaires.fff.endpoint.environment.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CreateEnvironmentRequest {

	@NotNull
	@Length(min = 3, max = 50)
	private String name;

	@NotNull
	@Positive
	private Integer projectId;

	@NotNull
	private Boolean forMe;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Boolean isForUser() {
		return forMe;
	}

	public void setForMe(Boolean forMe) {
		this.forMe = forMe;
	}

}
