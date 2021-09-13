package me.pedrocaires.fff.endpoint.account;

import me.pedrocaires.fff.daoutils.ExistsCallbackHandler;
import me.pedrocaires.fff.endpoint.account.model.Account;
import me.pedrocaires.fff.endpoint.account.model.CreateAccountRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AccountRepository {

	private final JdbcTemplate jdbcTemplate;

	private final AccountResultSetExtractor accountResultSetExtractor;

	private final ExistsCallbackHandler existsCallbackHandler;

	public AccountRepository(JdbcTemplate jdbcTemplate, AccountResultSetExtractor accountResultSetExtractor,
			ExistsCallbackHandler existsCallbackHandler) {
		this.jdbcTemplate = jdbcTemplate;
		this.accountResultSetExtractor = accountResultSetExtractor;
		this.existsCallbackHandler = existsCallbackHandler;
	}

	@Transactional
	public Account insert(CreateAccountRequest createAccountRequest, int userId) {
		var insertedAccount = jdbcTemplate.query("INSERT INTO ACCOUNTS(NAME) VALUES (?) RETURNING ID, NAME",
				accountResultSetExtractor.extractObject(), createAccountRequest.getName());

		jdbcTemplate.update("INSERT INTO ACCOUNTS_USERS (ACCOUNT_ID, USER_ID, ROLE_ID) VALUES (?, ?, ?)",
				insertedAccount.getId(), userId, 1);

		return insertedAccount;
	}

	public List<Account> getAccountsFromUserId(int userId){
		return jdbcTemplate.query("SELECT A.ID, NAME FROM ACCOUNTS A INNER JOIN ACCOUNTS_USERS AU ON A.ID = AU.ACCOUNT_ID WHERE AU.USER_ID = ?",
				accountResultSetExtractor.extractList(), userId);
	}

	public boolean isFromAccount(int accountId, int userId) {
		return jdbcTemplate.query(
				"SELECT EXISTS(SELECT ACCOUNT_ID, USER_ID FROM ACCOUNTS_USERS WHERE ACCOUNT_ID = ? AND USER_ID = ?)",
				existsCallbackHandler, accountId, userId);
	}

}
