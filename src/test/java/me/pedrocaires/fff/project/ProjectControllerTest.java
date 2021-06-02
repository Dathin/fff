package me.pedrocaires.fff.project;

import me.pedrocaires.fff.project.models.CreateProjectRequest;
import me.pedrocaires.fff.project.models.CreateProjectResponse;
import me.pedrocaires.fff.project.models.ProjectResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

	@Mock
	ProjectService projectService;

	@InjectMocks
	ProjectController projectController;

	@Test
	void shouldCreateProject() {
		var createProjectRequest = new CreateProjectRequest();
		var createProjectResponse = new CreateProjectResponse();
		when(projectService.createProjectForAccountId(createProjectRequest)).thenReturn(createProjectResponse);

		var createdUserResponseEntity = projectController.createProject(createProjectRequest);

		verify(projectService).createProjectForAccountId(createProjectRequest);
		assertEquals(HttpStatus.OK, createdUserResponseEntity.getStatusCode());
		assertEquals(createProjectResponse, createdUserResponseEntity.getBody());
	}

	@Test
	void shouldGetProjects() {
		var projectsResponse = new ArrayList<ProjectResponse>();
		when(projectService.getProjectsFromAccountId()).thenReturn(projectsResponse);

		var createdUserResponseEntity = projectController.getProjects();

		verify(projectService).getProjectsFromAccountId();
		assertEquals(HttpStatus.OK, createdUserResponseEntity.getStatusCode());
		assertEquals(projectsResponse, createdUserResponseEntity.getBody());
	}

}