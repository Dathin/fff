package me.pedrocaires.fff.endpoint.environment;

import me.pedrocaires.fff.daoutils.ExistsCallbackHandler;
import me.pedrocaires.fff.endpoint.environment.model.CreateEnvironmentRequest;
import me.pedrocaires.fff.endpoint.environment.model.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
				"INSERT INTO ENVIRONMENTS (NAME, PROJECT_ID, MAIN, USER_ID) VALUES (?, ?, ?, ?) RETURNING ID, NAME, PROJECT_ID, MAIN, USER_ID",
				resultSet -> {
					resultSet.next();
					var environment = new Environment();
					environment.setId(resultSet.getInt("ID"));
					environment.setName(resultSet.getString("NAME"));
					environment.setProjectId(resultSet.getInt("PROJECT_ID"));
					environment.setUserId(resultSet.getInt("USER_ID"));
					environment.setMain(resultSet.getBoolean("MAIN"));
					return environment;
				}, createEnvironmentRequest.getName(), createEnvironmentRequest.getProjectId(),
				createEnvironmentRequest.getMain(), userId);
	}

	public boolean isFromAccountId(int userId, int environmentId) {
		return jdbcTemplate.query(
				"SELECT EXISTS (SELECT 1 FROM USER_FEATURE_FLAG WHERE USER_ID = ? AND ENVIRONMENT_ID = ?)",
				existsCallbackHandler, userId, environmentId);
	}

}
