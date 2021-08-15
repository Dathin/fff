package me.pedrocaires.fff.endpoint.user;

import me.pedrocaires.fff.daoutils.ReturningCountCallbackHandler;
import me.pedrocaires.fff.endpoint.user.model.CreateUserRequest;
import me.pedrocaires.fff.endpoint.user.model.LoginRequest;
import me.pedrocaires.fff.endpoint.user.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

	private final JdbcTemplate jdbcTemplate;

	private final ReturningCountCallbackHandler returningCountCallbackHandler;

	private final UserResultSetExtractor userResultSetExtractor;

	public UserRepository(JdbcTemplate jdbcTemplate, ReturningCountCallbackHandler returningCountCallbackHandler,
			UserResultSetExtractor userResultSetExtractor) {
		this.jdbcTemplate = jdbcTemplate;
		this.returningCountCallbackHandler = returningCountCallbackHandler;
		this.userResultSetExtractor = userResultSetExtractor;
	}

	public User insert(CreateUserRequest createUserRequest) {
		return jdbcTemplate.query(
				"INSERT INTO USERS (IDENTIFIER, PASSWORD) VALUES (?, ?) RETURNING ID, IDENTIFIER, PASSWORD",
				userResultSetExtractor.extractObject(), createUserRequest.getIdentifier(),
				createUserRequest.getPassword());
	}

	public Optional<User> getPasswordToLogin(LoginRequest loginRequest) {
		return jdbcTemplate.query("SELECT * FROM USERS WHERE IDENTIFIER = ? LIMIT 1",
				userResultSetExtractor.extractOptional(), loginRequest.getIdentifier());
	}

	public int countByAccountId(int accountId) {
		return jdbcTemplate.query("SELECT COUNT(1) FROM USERS WHERE ACCOUNT_ID = ?", returningCountCallbackHandler,
				accountId);
	}

}
