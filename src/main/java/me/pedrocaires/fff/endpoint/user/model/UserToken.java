package me.pedrocaires.fff.endpoint.user.model;

import java.util.Date;

public class UserToken extends User {

	private Date expiresAt;

	public Date getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}

}
