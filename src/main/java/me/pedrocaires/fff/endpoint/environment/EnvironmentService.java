package me.pedrocaires.fff.endpoint.environment;

import me.pedrocaires.fff.endpoint.environment.model.Environment;
import me.pedrocaires.fff.endpoint.environment.model.CreateEnvironmentRequest;
import me.pedrocaires.fff.endpoint.environment.model.EnvironmentIntegrityException;
import me.pedrocaires.fff.endpoint.environment.model.MainEnvironmentAlreadyExistsException;
import me.pedrocaires.fff.endpoint.project.ProjectRepository;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentService {

	private final EnvironmentRepository environmentRepository;

	private final ProjectRepository projectRepository;

	public EnvironmentService(EnvironmentRepository environmentRepository, ProjectRepository projectRepository) {
		this.environmentRepository = environmentRepository;
		this.projectRepository = projectRepository;
	}

	public Environment createEnvironment(CreateEnvironmentRequest createEnvironmentRequest, int userId) {
		try {
			if (projectRepository.isFromUser(createEnvironmentRequest.getProjectId(), userId)) {
				return environmentRepository.insert(createEnvironmentRequest,
						createEnvironmentRequest.isForUser() ? userId : null);
			}
		} catch(DuplicateKeyException ex) {
			if(ex.getMessage().contains("environment_project_id_main")) {
				throw new MainEnvironmentAlreadyExistsException();
			}
			throw ex;
		}

		throw new EnvironmentIntegrityException();
	}

	public boolean isFromAccountId(int environmentId, int accountId) {
		return environmentRepository.isFromAccountId(environmentId, accountId);
	}

}
