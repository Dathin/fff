package me.pedrocaires.fff.endpoint.featureflag.model;

public class FeatureFlagResponse {

	private int id;

	private boolean value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

}
