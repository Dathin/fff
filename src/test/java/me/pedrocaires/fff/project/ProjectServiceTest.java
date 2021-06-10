package me.pedrocaires.fff.project;

import me.pedrocaires.fff.exception.UnauthorizedException;
import me.pedrocaires.fff.project.models.CreateProjectRequest;
import me.pedrocaires.fff.project.models.Project;
import me.pedrocaires.fff.user.UserService;
import me.pedrocaires.fff.user.model.UserToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

	PodamFactory podamFactory = new PodamFactoryImpl();

	@Mock
	ProjectRepository projectRepository;

	@Spy
	ProjectMapper projectMapper = new ProjectMapperImpl();

	@Mock
	UserService userService;

	@InjectMocks
	ProjectService projectService;

	// @Test
	// void shouldCreateProjectForAccountId() {
	// var accountId = 1;
	// var userToken = podamFactory.manufacturePojo(UserToken.class);
	// userToken.setAccountId(accountId);
	// var createProjectRequest =
	// podamFactory.manufacturePojo(CreateProjectRequest.class);
	// var project = podamFactory.manufacturePojo(Project.class);
	// when(userService.getAuthenticatedUser()).thenReturn(Optional.of(userToken));
	// when(projectRepository.insert(createProjectRequest,
	// accountId)).thenReturn(project);
	//
	// projectService.createProjectForAccountId(createProjectRequest);
	//
	// verify(projectRepository).insert(createProjectRequest, accountId);
	// verify(projectMapper).projectToCreateProjectResponse(project);
	// }

	@Test
	void shouldThrowWhenCreateProjectWithInvalidAuthentication() {
		var createProjectRequest = podamFactory.manufacturePojo(CreateProjectRequest.class);
		when(userService.getAuthenticatedUser()).thenReturn(Optional.empty());

		// assertThrows(UnauthorizedException.class, () ->
		// projectService.createProjectForAccountId(createProjectRequest));
	}

	@Test
	void shouldGetProjectsForAccountId() {
		var accountId = 1;
		var userToken = podamFactory.manufacturePojo(UserToken.class);
		userToken.setAccountId(accountId);
		var projects = podamFactory.manufacturePojo(ArrayList.class, Project.class);
		when(userService.getAuthenticatedUser()).thenReturn(Optional.of(userToken));
		when(projectRepository.getProjectsByAccountId(accountId)).thenReturn(projects);

		// projectService.getProjectsFromAccountId();

		verify(projectRepository).getProjectsByAccountId(accountId);
		verify(projectMapper).projectsToProjectsResponse(projects);
	}

	@Test
	void shouldThrowWhenGetProjectsWithInvalidAuthentication() {
		when(userService.getAuthenticatedUser()).thenReturn(Optional.empty());

		// assertThrows(UnauthorizedException.class, () ->
		// projectService.getProjectsFromAccountId());
	}

}