package me.pedrocaires.fff.endpoint.project;

import me.pedrocaires.fff.endpoint.project.ProjectController;
import me.pedrocaires.fff.endpoint.project.ProjectService;
import me.pedrocaires.fff.endpoint.project.models.CreateProjectRequest;
import me.pedrocaires.fff.endpoint.project.models.CreateProjectResponse;
import me.pedrocaires.fff.endpoint.project.models.ProjectResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

	PodamFactory podamFactory = new PodamFactoryImpl();

	@Mock
	ProjectService projectService;

	@InjectMocks
	ProjectController projectController;

	@Test
	void shouldCreateProject() {

		var createProjectRequest = podamFactory.manufacturePojo(CreateProjectRequest.class);
		var createProjectResponse = podamFactory.manufacturePojo(CreateProjectResponse.class);
		// when(projectService.createProjectForAccountId(createProjectRequest)).thenReturn(createProjectResponse);

		var createdUserResponseEntity = projectController.createProject(createProjectRequest);

		// verify(projectService).createProjectForAccountId(createProjectRequest);
		assertEquals(HttpStatus.OK, createdUserResponseEntity.getStatusCode());
		assertEquals(createProjectResponse, createdUserResponseEntity.getBody());
	}

	// @Test
	// void shouldGetProjects() {
	// 	var projectsResponse = podamFactory.manufacturePojo(ArrayList.class, ProjectResponse.class);
	// 	// when(projectService.getProjectsFromAccountId()).thenReturn(projectsResponse);

	// 	var createdUserResponseEntity = projectController.getProjects();

	// 	// verify(projectService).getProjectsFromAccountId();
	// 	assertEquals(HttpStatus.OK, createdUserResponseEntity.getStatusCode());
	// 	assertEquals(projectsResponse, createdUserResponseEntity.getBody());
	// }

}