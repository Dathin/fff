package me.pedrocaires.fff.environment;

import me.pedrocaires.fff.environment.model.CreateEnvironmentRequest;
import me.pedrocaires.fff.environment.model.CreateEnvironmentResponse;
import me.pedrocaires.fff.exception.InvalidProject;
import me.pedrocaires.fff.exception.UnauthorizedException;
import me.pedrocaires.fff.project.ProjectService;
import me.pedrocaires.fff.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentService {

    private final EnvironmentRepository environmentRepository;

    private final EnvironmentMapper environmentMapper;

    private final UserService userService;

    private final ProjectService projectService;

    public EnvironmentService(EnvironmentRepository environmentRepository, EnvironmentMapper environmentMapper, UserService userService, ProjectService projectService) {
        this.environmentRepository = environmentRepository;
        this.environmentMapper = environmentMapper;
        this.userService = userService;
        this.projectService = projectService;
    }

    public CreateEnvironmentResponse createEnvironment(CreateEnvironmentRequest createEnvironmentRequest) {
        if (isProjectIdFromAccount(createEnvironmentRequest.getProjectId())){
            var userId = userService.getAuthenticatedUser().orElseThrow(UnauthorizedException::new).getId();
            if (createEnvironmentRequest.isForMe()){
                var createdEnvironment = environmentRepository.insert(createEnvironmentRequest, userId);
            }else {
                var createdEnvironment = environmentRepository.insert(createEnvironmentRequest, null);
            }
            return environmentMapper.environmentTocreateEnvironmentResponse(createEnvironmentRequest);
        }
        throw new InvalidProject();
    }

    private boolean isProjectIdFromAccount(int projectId){
        var project = projectService.getProjectsFromAccountId();
        return project.stream().anyMatch(projectResponse -> projectResponse.getId() == projectId);
    }


//    public List<EnvironmentResponse> getEnvironments() {
//        var accountId = userService.getAuthenticatedUser().orElseThrow(UnauthorizedException::new).getAccountId();
//        var environments = environmentRepository.getEnvironmentByAccountId();
//        return environmentMapper.environmentsToEnvironmentsResponse(environments);
//    }
}
