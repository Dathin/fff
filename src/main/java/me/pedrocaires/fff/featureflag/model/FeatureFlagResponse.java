package me.pedrocaires.fff.featureflag.model;

import java.io.Serializable;

public class FeatureFlagResponse implements Serializable {

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
