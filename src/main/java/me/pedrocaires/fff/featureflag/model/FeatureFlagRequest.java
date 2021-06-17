package me.pedrocaires.fff.featureflag.model;

import me.pedrocaires.fff.validator.CheckAtLeastOneNotNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Positive;
import java.util.Objects;

@CheckAtLeastOneNotNull(message = "id or name must not be null", fieldNames = {"id", "name"})
public class FeatureFlagRequest {

	@Positive
	private Integer id;

	@Length(min = 3, max = 50)
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FeatureFlagRequest that = (FeatureFlagRequest) o;
		return id == that.id && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
