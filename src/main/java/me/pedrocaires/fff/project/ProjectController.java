package me.pedrocaires.fff.project;

import me.pedrocaires.fff.project.models.CreateProjectRequest;
import me.pedrocaires.fff.project.models.CreateProjectResponse;
import me.pedrocaires.fff.project.models.ProjectResponse;
import me.pedrocaires.fff.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

	private final ProjectService projectService;

	private final UserService userService;

	public ProjectController(ProjectService projectService, UserService userService) {
		this.projectService = projectService;
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<ProjectResponse>> getProjects() {
		var accountId = userService.getOrThrowAuthenticatedUser().getAccountId();
		return ResponseEntity.ok(projectService.getProjectsFromAccountId(accountId));
	}

	@PostMapping
	public ResponseEntity<CreateProjectResponse> createProject(@RequestBody CreateProjectRequest createProjectRequest) {
		var accountId = userService.getOrThrowAuthenticatedUser().getAccountId();
		return ResponseEntity.ok(projectService.createProjectForAccountId(createProjectRequest, accountId));
	}

}
