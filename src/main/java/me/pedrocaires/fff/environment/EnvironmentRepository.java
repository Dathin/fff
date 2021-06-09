package me.pedrocaires.fff.environment;

import me.pedrocaires.fff.environment.model.CreateEnvironmentRequest;
import me.pedrocaires.fff.environment.model.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EnvironmentRepository {

    private final JdbcTemplate jdbcTemplate;

    public EnvironmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Environment insert(CreateEnvironmentRequest createEnvironmentRequest, int userId) {
        return jdbcTemplate.query("INSERT INTO ENVIRONMENTS (NAME, PROJECT_ID, USER_ID) VALUES (?, ?, ?) RETURNING NAME, PROJECT_ID, USER_ID", resultSet -> {
            resultSet.next();
            return environmentResultSet(resultSet);
        }, createEnvironmentRequest.getName(), createEnvironmentRequest.getProjectId(), userId);
    }

//    public List<Environment> getEnvironmentByAccountId() {
//        return jdbcTemplate.query("SELECT ID, NAME, PROJECT_ID, USER_ID FROM ENVIRONMENTS WHERE ACCOUNT")
//    }

    private Environment environmentResultSet(ResultSet resultSet) throws SQLException {
        var environment = new Environment();
        environment.setId(resultSet.getInt("ID"));
        environment.setName(resultSet.getString("NAME"));
        environment.setProjectId(resultSet.getInt("PROJECT_ID"));
        environment.setUserId(resultSet.getInt("USER_ID"));
        return environment;
    }
}
