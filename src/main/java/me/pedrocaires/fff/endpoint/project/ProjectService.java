package me.pedrocaires.fff.endpoint.project;

import me.pedrocaires.fff.endpoint.project.models.CreateProjectRequest;
import me.pedrocaires.fff.endpoint.project.models.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

	private final ProjectRepository projectRepository;

	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public List<Project> getProjectsFromAccountId(int accountId) {
		return projectRepository.getProjectsByAccountId(accountId);
	}

	public Project createProjectForAccountId(CreateProjectRequest createProjectRequest, int accountId) {
		return projectRepository.insert(createProjectRequest, accountId);
	}

	public boolean isFromAccountId(int projectId, int accountId) {
		return projectRepository.isFromAccountId(projectId, accountId);
	}

}
