package me.pedrocaires.fff.account;

import me.pedrocaires.fff.account.model.Account;
import me.pedrocaires.fff.account.model.CreateAccountRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountRepository {

	private final JdbcTemplate jdbcTemplate;

	public AccountRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional
	public Account insert(CreateAccountRequest createAccountRequest, int userId) {
			var insertedAccount = jdbcTemplate.query("INSERT INTO ACCOUNTS(NAME) VALUES (?) RETURNING ID, NAME", resultSet -> {
				resultSet.next();
				var account = new Account();
				account.setId(resultSet.getInt("ID"));
				account.setName(resultSet.getString("NAME"));
				return account;
			}, createAccountRequest.getName());

			jdbcTemplate.update("INSERT INTO ACCOUNTS_USERS (ACCOUNT_ID, USER_ID, ROLE_ID) VALUES (?, ?, ?)", insertedAccount.getId(), userId, 1);

			return insertedAccount;
	}

}
