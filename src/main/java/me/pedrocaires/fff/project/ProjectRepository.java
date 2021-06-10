package me.pedrocaires.fff.project;

import me.pedrocaires.fff.daoutils.ExistsCallbackHandler;
import me.pedrocaires.fff.daoutils.resultsetextractor.ProjectResultSetExtractor;
import me.pedrocaires.fff.project.models.CreateProjectRequest;
import me.pedrocaires.fff.project.models.Project;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository {

	private final JdbcTemplate jdbcTemplate;

	private final ExistsCallbackHandler existsCallbackHandler;

	private final ProjectResultSetExtractor projectResultSetExtractor;

	public ProjectRepository(JdbcTemplate jdbcTemplate, ExistsCallbackHandler existsCallbackHandler, ProjectResultSetExtractor projectResultSetExtractor) {
		this.jdbcTemplate = jdbcTemplate;
		this.existsCallbackHandler = existsCallbackHandler;
		this.projectResultSetExtractor = projectResultSetExtractor;
	}

	public List<Project> getProjectsByAccountId(int accountId) {
		return jdbcTemplate.query("SELECT ID, NAME, ACCOUNT_ID FROM PROJECTS WHERE ACCOUNT_ID = ?", projectResultSetExtractor.extractList(), accountId);
	}

	public Project insert(CreateProjectRequest createProjectRequest, int accountId) {
		return jdbcTemplate.query(
				"INSERT INTO PROJECTS (NAME, ACCOUNT_ID) VALUES (?, ?) RETURNING ID, NAME, ACCOUNT_ID", projectResultSetExtractor.extractObject(), createProjectRequest.getName(), accountId);
	}

	public boolean isFromAccountId(int projectId, int accountId) {
		return jdbcTemplate.query("SELECT EXISTS ( SELECT 1 FROM PROJECTS WHERE ID = ? AND ACCOUNT_ID = ?)",
				existsCallbackHandler, projectId, accountId);
	}

}
