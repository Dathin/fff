package me.pedrocaires.fff.endpoint.user.model;

import java.security.Principal;

public class User implements Principal {

	private int id;

	private String identifier;

	private int accountId;

	private String password;

	private Boolean webClient;

	public int getId() {
		return id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return getIdentifier();
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getWebClient() {
		return webClient;
	}

	public void setWebClient(Boolean webClient) {
		this.webClient = webClient;
	}
}
