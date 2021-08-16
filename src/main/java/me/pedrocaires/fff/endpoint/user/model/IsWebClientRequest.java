package me.pedrocaires.fff.endpoint.user.model;

import me.pedrocaires.fff.validator.WebClientUser;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@WebClientUser
public class IsWebClientRequest {

    private boolean WebClient;

    @NotNull
    @Length(min = 3, max = 320)
    private String identifier;

    public boolean isWebClient() {
        return WebClient;
    }

    public void setWebClient(boolean webClient) {
        WebClient = webClient;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
