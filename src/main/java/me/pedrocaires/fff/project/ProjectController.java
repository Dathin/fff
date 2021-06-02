package me.pedrocaires.fff.project;

import me.pedrocaires.fff.project.models.CreateProjectRequest;
import me.pedrocaires.fff.project.models.CreateProjectResponse;
import me.pedrocaires.fff.project.models.ProjectResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getProjects(){
        return ResponseEntity.ok(projectService.getProjectsFromAccountId());
    }

    @PostMapping
    public ResponseEntity<CreateProjectResponse> createProject(@RequestBody CreateProjectRequest createProjectRequest){
        return ResponseEntity.ok(projectService.createProjectForAccountId(createProjectRequest));
    }

}
