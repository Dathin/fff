package me.pedrocaires.fff.endpoint.environment;

import me.pedrocaires.fff.endpoint.environment.model.Environment;
import me.pedrocaires.fff.endpoint.environment.model.CreateEnvironmentRequest;
import me.pedrocaires.fff.endpoint.project.ProjectIntegrityException;
import me.pedrocaires.fff.endpoint.project.ProjectService;
import me.pedrocaires.fff.endpoint.user.UserService;
import me.pedrocaires.fff.endpoint.user.model.UserToken;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentService {

	private final EnvironmentRepository environmentRepository;

	private final ProjectService projectService;

	public EnvironmentService(EnvironmentRepository environmentRepository, EnvironmentMapper environmentMapper,
			UserService userService, ProjectService projectService) {
		this.environmentRepository = environmentRepository;
		this.projectService = projectService;
	}

	public Environment createEnvironment(CreateEnvironmentRequest createEnvironmentRequest, UserToken userToken) {
		if (projectService.isFromAccountId(createEnvironmentRequest.getProjectId(), userToken.getAccountId())) {
			final Environment environment;
			if (createEnvironmentRequest.isForUser()) {
				environment = environmentRepository.insert(createEnvironmentRequest, userToken.getId());
			}
			else {
				environment = environmentRepository.insert(createEnvironmentRequest, null);
			}
			return environment;
		}
		throw new ProjectIntegrityException();
	}

	public boolean isFromAccountId(int environmentId, int accountId) {
		return environmentRepository.isFromAccountId(environmentId, accountId);
	}

	// public List<EnvironmentResponse> getEnvironments() {
	// var accountId =
	// userService.getAuthenticatedUser().orElseThrow(UnauthorizedException::new).getAccountId();
	// var environments = environmentRepository.getEnvironmentByAccountId();
	// return environmentMapper.environmentsToEnvironmentsResponse(environments);
	// }

}