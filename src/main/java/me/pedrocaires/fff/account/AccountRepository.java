package me.pedrocaires.fff.account;

import me.pedrocaires.fff.account.model.Account;
import me.pedrocaires.fff.account.model.CreateAccountRequest;
import me.pedrocaires.fff.daoutils.ReturningIdCallbackHandler;
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

    public Account insert(CreateAccountRequest createAccountRequest){
        return jdbcTemplate.query("INSERT INTO ACCOUNTS(NAME) VALUES (?) RETURNING ID, NAME",
                resultSet -> {
                    resultSet.next();
                    var account = new Account();
                    account.setId(resultSet.getInt("ID"));
                    account.setName(resultSet.getString("NAME"));
                    return account;
                }, createAccountRequest.getName());
    }
}
