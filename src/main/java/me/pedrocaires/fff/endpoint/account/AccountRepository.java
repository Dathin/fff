package me.pedrocaires.fff.endpoint.account;

import me.pedrocaires.fff.endpoint.account.model.Account;
import me.pedrocaires.fff.endpoint.account.model.CreateAccountRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountRepository {

	private final JdbcTemplate jdbcTemplate;

	private final AccountResultSetExtractor accountResultSetExtractor;

	public AccountRepository(JdbcTemplate jdbcTemplate, AccountResultSetExtractor accountResultSetExtractor) {
		this.jdbcTemplate = jdbcTemplate;
		this.accountResultSetExtractor = accountResultSetExtractor;
	}

	@Transactional
	public Account insert(CreateAccountRequest createAccountRequest, int userId) {
		var insertedAccount = jdbcTemplate.query("INSERT INTO ACCOUNTS(NAME) VALUES (?) RETURNING ID, NAME",
				accountResultSetExtractor.extractObject(), createAccountRequest.getName());

		jdbcTemplate.update("INSERT INTO ACCOUNTS_USERS (ACCOUNT_ID, 2, ROLE_ID) VALUES (?, ?, ?)",
				insertedAccount.getId(), userId, 1);

		return insertedAccount;
	}

}
