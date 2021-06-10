package me.pedrocaires.fff.user;

import me.pedrocaires.fff.daoutils.ReturningCountCallbackHandler;
import me.pedrocaires.fff.daoutils.resultsetextractor.UserResultSetExtractor;
import me.pedrocaires.fff.user.model.CreateUserRequest;
import me.pedrocaires.fff.user.model.LoginRequest;
import me.pedrocaires.fff.user.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserRepository {

	private final JdbcTemplate jdbcTemplate;

	private final ReturningCountCallbackHandler returningCountCallbackHandler;

	private final UserResultSetExtractor userResultSetExtractor;


	public UserRepository(JdbcTemplate jdbcTemplate, ReturningCountCallbackHandler returningCountCallbackHandler, UserResultSetExtractor userResultSetExtractor) {
		this.jdbcTemplate = jdbcTemplate;
		this.returningCountCallbackHandler = returningCountCallbackHandler;
		this.userResultSetExtractor = userResultSetExtractor;
	}

	public User insert(CreateUserRequest createUserRequest) {
		return jdbcTemplate.query(
				"INSERT INTO USERS (NAME, ACCOUNT_ID, PASSWORD) VALUES (?, ?, ?) RETURNING ID, NAME, ACCOUNT_ID, PASSWORD",
				userResultSetExtractor.extractObject(), createUserRequest.getName(), createUserRequest.getAccountId(),
				createUserRequest.getPassword());
	}

	public Optional<User> getPasswordToLogin(LoginRequest loginRequest) {
		return jdbcTemplate.query("SELECT * FROM USERS WHERE ACCOUNT_ID = ? AND NAME = ? LIMIT 1",
				userResultSetExtractor.extractOptional(), loginRequest.getAccountId(), loginRequest.getName());
	}

	public int countByAccountId(int accountId) {
		return jdbcTemplate.query("SELECT COUNT(1) FROM USERS WHERE ACCOUNT_ID = ?", returningCountCallbackHandler,
				accountId);
	}

}
