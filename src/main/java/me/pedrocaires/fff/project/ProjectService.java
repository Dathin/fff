package me.pedrocaires.fff.project;

import me.pedrocaires.fff.project.models.CreateProjectRequest;
import me.pedrocaires.fff.project.models.CreateProjectResponse;
import me.pedrocaires.fff.project.models.ProjectResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

	private final ProjectRepository projectRepository;

	private final ProjectMapper projectMapper;

	public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
		this.projectRepository = projectRepository;
		this.projectMapper = projectMapper;
	}

	public List<ProjectResponse> getProjectsFromAccountId(int accountId) {
		var projects = projectRepository.getProjectsByAccountId(accountId);
		return projectMapper.projectsToProjectsResponse(projects);
	}

	public CreateProjectResponse createProjectForAccountId(CreateProjectRequest createProjectRequest, int accountId) {
		var project = projectRepository.insert(createProjectRequest, accountId);
		return projectMapper.projectToCreateProjectResponse(project);
	}

	public boolean isFromAccountId(int projectId, int accountId) {
		return projectRepository.isFromAccountId(projectId, accountId);
	}

}
