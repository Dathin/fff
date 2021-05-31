package me.pedrocaires.fff.account;

import me.pedrocaires.fff.account.model.Account;
import me.pedrocaires.fff.account.model.CreateAccountRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {

    private final JdbcTemplate jdbcTemplate;

    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
