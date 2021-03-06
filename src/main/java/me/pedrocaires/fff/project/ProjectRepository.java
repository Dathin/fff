package me.pedrocaires.fff.project;

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

	public ProjectRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Project> getProjectsByAccountId(int accountId) {
		return jdbcTemplate.query("SELECT ID, NAME, ACCOUNT_ID FROM PROJECTS WHERE ACCOUNT_ID = ?", resultSet -> {
			var projects = new ArrayList<Project>();
			while (resultSet.next()) {
				projects.add(resultSetProject(resultSet));
			}
			return projects;
		}, accountId);
	}

	public Project insert(CreateProjectRequest createProjectRequest, int accountId) {
		return jdbcTemplate.query(
				"INSERT INTO PROJECTS (NAME, ACCOUNT_ID) VALUES (?, ?) RETURNING ID, NAME, ACCOUNT_ID", resultSet -> {
					resultSet.next();
					return resultSetProject(resultSet);
				}, createProjectRequest.getName(), accountId);
	}

	private Project resultSetProject(ResultSet resultSet) throws SQLException {
		var project = new Project();
		project.setId(resultSet.getInt("ID"));
		project.setName(resultSet.getString("NAME"));
		project.setAccountId(resultSet.getInt("ACCOUNT_ID"));
		return project;
	}

}
