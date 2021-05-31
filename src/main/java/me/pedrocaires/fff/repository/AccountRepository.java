package me.pedrocaires.fff.repository;

import me.pedrocaires.fff.domain.request.CreateAccountRequest;
import me.pedrocaires.fff.domain.response.CreateAccountResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ReturningIdCallbackHandler returningIdCallbackHandler;

    public AccountRepository(JdbcTemplate jdbcTemplate, ReturningIdCallbackHandler returningIdCallbackHandler) {
        this.jdbcTemplate = jdbcTemplate;
        this.returningIdCallbackHandler = returningIdCallbackHandler;
    }

    public int insert(CreateAccountRequest createAccountRequest){
        return jdbcTemplate.query("INSERT INTO ACCOUNTS(NAME) VALUES (?) RETURNING ID",
                returningIdCallbackHandler, createAccountRequest.getName());
    }
}
