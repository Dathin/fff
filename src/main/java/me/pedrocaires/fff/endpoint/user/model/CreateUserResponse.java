package me.pedrocaires.fff.endpoint.user.model;

public class CreateUserResponse {

	private int id;

	private String identifier;

	private boolean webClient;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public boolean isWebClient() {
		return webClient;
	}

	public void setWebClient(boolean webClient) {
		this.webClient = webClient;
	}
}
