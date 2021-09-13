package me.pedrocaires.fff.endpoint.project;

import me.pedrocaires.fff.endpoint.project.models.CreateProjectRequest;
import me.pedrocaires.fff.endpoint.project.models.CreateProjectResponse;
import me.pedrocaires.fff.endpoint.project.models.ProjectResponse;
import me.pedrocaires.fff.endpoint.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

	private final ProjectService projectService;

	private final UserService userService;

	private final ProjectMapper projectMapper;

	public ProjectController(ProjectService projectService, UserService userService, ProjectMapper projectMapper) {
		this.projectService = projectService;
		this.userService = userService;
		this.projectMapper = projectMapper;
	}

	@GetMapping
	public ResponseEntity<List<ProjectResponse>> getProjects(Integer accountId) {
		var userId = userService.getOrThrowAuthenticatedUser().getId();
		var projects = projectService.getProjectsFromAccountId(accountId, userId);
		return ResponseEntity.ok(projectMapper.projectsToProjectsResponse(projects));
	}

	@PostMapping
	public ResponseEntity<CreateProjectResponse> createProject(
			@Valid @RequestBody CreateProjectRequest createProjectRequest) {
		var user = userService.getOrThrowAuthenticatedUser();
		var createdProject = projectService.createProjectForUser(createProjectRequest, user.getId());
		return ResponseEntity.ok(projectMapper.projectToCreateProjectResponse(createdProject));
	}

}
