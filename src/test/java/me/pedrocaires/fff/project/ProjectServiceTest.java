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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    ProjectRepository projectRepository;

    @Mock
    ProjectMapper projectMapper;

    @Mock
    UserService userService;

    @InjectMocks
    ProjectService projectService;

    @Test
    void shouldCreateProjectForAccountId() {
        var accountId = 1;
        var userToken = new UserToken();
        userToken.setAccountId(accountId);
        var createProjectRequest = new CreateProjectRequest();
        var project = new Project();
        when(userService.getAuthenticatedUser()).thenReturn(Optional.of(userToken));
        when(projectRepository.insert(createProjectRequest, accountId)).thenReturn(project);

        projectService.createProjectForAccountId(createProjectRequest);

        verify(projectRepository).insert(createProjectRequest, accountId);
        verify(projectMapper).projectToCreateProjectResponse(project);
    }

    @Test
    void shouldThrowWhenCreateProjectWithInvalidAuthentication() {
        var createProjectRequest = new CreateProjectRequest();
        when(userService.getAuthenticatedUser()).thenReturn(Optional.empty());

        assertThrows(UnauthorizedException.class, () -> projectService.createProjectForAccountId(createProjectRequest));
    }

    @Test
    void shouldGetProjectsForAccountId() {
        var accountId = 1;
        var userToken = new UserToken();
        userToken.setAccountId(accountId);
        var projects = new ArrayList<Project>();
        when(userService.getAuthenticatedUser()).thenReturn(Optional.of(userToken));
        when(projectRepository.getProjectsByAccountId(accountId)).thenReturn(projects);

        projectService.getProjectsFromAccountId();

        verify(projectRepository).getProjectsByAccountId(accountId);
        verify(projectMapper).projectsToProjectsResponse(projects);
    }

    @Test
    void shouldThrowWhenGetProjectsWithInvalidAuthentication() {
        when(userService.getAuthenticatedUser()).thenReturn(Optional.empty());

        assertThrows(UnauthorizedException.class, () -> projectService.getProjectsFromAccountId());
    }
}