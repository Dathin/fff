package me.pedrocaires.fff.featureflag.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CreateFeatureFlagRequest {

	@NotNull
	@Positive
	private Integer environmentId;

	@NotNull
	@Length(min = 3, max = 50)
	private String name;

	@NotNull
	private Boolean value;

	public Integer getEnvironmentId() {
		return environmentId;
	}

	public void setEnvironmentId(Integer environmentId) {
		this.environmentId = environmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean isValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

}
