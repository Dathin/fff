package me.pedrocaires.fff.endpoint.project;

import me.pedrocaires.fff.daoutils.ExistsCallbackHandler;
import me.pedrocaires.fff.endpoint.project.models.CreateProjectRequest;
import me.pedrocaires.fff.endpoint.project.models.Project;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {

	private final JdbcTemplate jdbcTemplate;

	private final ExistsCallbackHandler existsCallbackHandler;

	private final ProjectResultSetExtractor projectResultSetExtractor;

	public ProjectRepository(JdbcTemplate jdbcTemplate, ExistsCallbackHandler existsCallbackHandler,
			ProjectResultSetExtractor projectResultSetExtractor) {
		this.jdbcTemplate = jdbcTemplate;
		this.existsCallbackHandler = existsCallbackHandler;
		this.projectResultSetExtractor = projectResultSetExtractor;
	}

	public List<Project> getProjectsByAccountId(Integer accountId, int userId) {
		return jdbcTemplate.query("SELECT PROJECT_ID AS ID, PROJECT_NAME AS NAME, ACCOUNT_ID FROM USER_FEATURE_FLAG WHERE ACCOUNT_ID = ? AND USER_ID = ?",
				projectResultSetExtractor.extractList(), accountId, userId);
	}

	public Project insert(CreateProjectRequest createProjectRequest) {
		return jdbcTemplate.query(
				"INSERT INTO PROJECTS (NAME, ACCOUNT_ID) VALUES (?, ?) RETURNING ID, NAME, ACCOUNT_ID",
				projectResultSetExtractor.extractObject(), createProjectRequest.getName(),
				createProjectRequest.getAccountId());
	}

	public boolean isFromAccountId(int projectId, int accountId) {
		return jdbcTemplate.query("SELECT EXISTS ( SELECT 1 FROM PROJECTS WHERE ID = ? AND ACCOUNT_ID = ?)",
				existsCallbackHandler, projectId, accountId);
	}

	public boolean isFromUser(int projectId, int userId) {
		return jdbcTemplate.query(
				"SELECT EXISTS ( SELECT 1 FROM PROJECTS AS P INNER JOIN ACCOUNTS AS A ON P.ACCOUNT_ID = A.ID INNER JOIN ACCOUNTS_USERS AS AU ON A.ID = AU.ACCOUNT_ID WHERE P.ID = ? AND AU.USER_ID = ?)",
				existsCallbackHandler, projectId, userId);
	}

}
