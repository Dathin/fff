package me.pedrocaires.fff.environment;

import me.pedrocaires.fff.daoutils.ExistsCallbackHandler;
import me.pedrocaires.fff.environment.model.CreateEnvironmentRequest;
import me.pedrocaires.fff.environment.model.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class EnvironmentRepository {

	private final JdbcTemplate jdbcTemplate;

	private final ExistsCallbackHandler existsCallbackHandler;

	public EnvironmentRepository(JdbcTemplate jdbcTemplate, ExistsCallbackHandler existsCallbackHandler) {
		this.jdbcTemplate = jdbcTemplate;
		this.existsCallbackHandler = existsCallbackHandler;
	}

	public Environment insert(CreateEnvironmentRequest createEnvironmentRequest, Integer userId) {
		return jdbcTemplate.query(
				"INSERT INTO ENVIRONMENTS (NAME, PROJECT_ID, USER_ID) VALUES (?, ?, ?) RETURNING ID, NAME, PROJECT_ID, USER_ID",
				resultSet -> {
					resultSet.next();
					return environmentResultSet(resultSet);
				}, createEnvironmentRequest.getName(), createEnvironmentRequest.getProjectId(), userId);
	}

	private Environment environmentResultSet(ResultSet resultSet) throws SQLException {
		var environment = new Environment();
		environment.setId(resultSet.getInt("ID"));
		environment.setName(resultSet.getString("NAME"));
		environment.setProjectId(resultSet.getInt("PROJECT_ID"));
		environment.setUserId(resultSet.getInt("USER_ID"));
		return environment;
	}

	public boolean isFromAccountId(int environmentId, int accountId) {
		return jdbcTemplate.query(
				"SELECT EXISTS (SELECT 1 FROM ENVIRONMENTS AS E INNER JOIN PROJECTS AS P ON E.PROJECT_ID = P.ID WHERE E.ID = ? AND P.ACCOUNT_ID = ?)",
				existsCallbackHandler, environmentId, accountId);
	}

}
