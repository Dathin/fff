package me.pedrocaires.fff.project;

import me.pedrocaires.fff.exception.UnauthorizedException;
import me.pedrocaires.fff.project.models.CreateProjectRequest;
import me.pedrocaires.fff.project.models.CreateProjectResponse;
import me.pedrocaires.fff.project.models.ProjectResponse;
import me.pedrocaires.fff.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

	private final ProjectRepository projectRepository;

	private final ProjectMapper projectMapper;

	private final UserService userService;

	public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper, UserService userService) {
		this.projectRepository = projectRepository;
		this.projectMapper = projectMapper;
		this.userService = userService;
	}

	public List<ProjectResponse> getProjectsFromAccountId() {
		var accountId = userService.getAuthenticatedUser().orElseThrow(UnauthorizedException::new).getAccountId();
		var projects = projectRepository.getProjectsByAccountId(accountId);
		return projectMapper.projectsToProjectsResponse(projects);
	}

	public CreateProjectResponse createProjectForAccountId(CreateProjectRequest createProjectRequest) {
		var accountId = userService.getAuthenticatedUser().orElseThrow(UnauthorizedException::new).getAccountId();
		var project = projectRepository.insert(createProjectRequest, accountId);
		return projectMapper.projectToCreateProjectResponse(project);
	}

}
