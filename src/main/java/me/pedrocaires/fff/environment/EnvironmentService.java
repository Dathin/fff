package me.pedrocaires.fff.environment;

import me.pedrocaires.fff.environment.model.CreateEnvironmentRequest;
import me.pedrocaires.fff.environment.model.CreateEnvironmentResponse;
import me.pedrocaires.fff.environment.model.Environment;
import me.pedrocaires.fff.exception.integrity.ProjectIntegrityException;
import me.pedrocaires.fff.exception.UnauthorizedException;
import me.pedrocaires.fff.project.ProjectService;
import me.pedrocaires.fff.user.UserService;
import me.pedrocaires.fff.user.model.UserToken;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentService {

	private final EnvironmentRepository environmentRepository;

	private final EnvironmentMapper environmentMapper;

	private final ProjectService projectService;

	public EnvironmentService(EnvironmentRepository environmentRepository, EnvironmentMapper environmentMapper,
			UserService userService, ProjectService projectService) {
		this.environmentRepository = environmentRepository;
		this.environmentMapper = environmentMapper;
		this.projectService = projectService;
	}

	public CreateEnvironmentResponse createEnvironment(CreateEnvironmentRequest createEnvironmentRequest,
			UserToken userToken) {
		if (projectService.isFromAccountId(createEnvironmentRequest.getProjectId(), userToken.getAccountId())) {
			final Environment environment;
			if (createEnvironmentRequest.isForUser()) {
				environment = environmentRepository.insert(createEnvironmentRequest, userToken.getId());
			}
			else {
				environment = environmentRepository.insert(createEnvironmentRequest, null);
			}
			return environmentMapper.environmentToCreateEnvironmentResponse(environment);
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
