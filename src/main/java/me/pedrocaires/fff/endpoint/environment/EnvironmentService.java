package me.pedrocaires.fff.endpoint.environment;

import me.pedrocaires.fff.endpoint.environment.model.Environment;
import me.pedrocaires.fff.daoutils.DuplicateKeyHandler;
import me.pedrocaires.fff.daoutils.model.DuplicateKeyHandlerRequest;
import me.pedrocaires.fff.endpoint.environment.model.CreateEnvironmentRequest;
import me.pedrocaires.fff.endpoint.environment.model.EnvironmentIntegrityException;
import me.pedrocaires.fff.endpoint.environment.model.MainEnvironmentAlreadyExistsException;
import me.pedrocaires.fff.endpoint.project.ProjectRepository;

import java.util.Arrays;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentService {

	private final EnvironmentRepository environmentRepository;

	private final ProjectRepository projectRepository;

	private final DuplicateKeyHandler duplicateKeyHandler;


	public EnvironmentService(EnvironmentRepository environmentRepository, ProjectRepository projectRepository, DuplicateKeyHandler duplicateKeyHandler) {
		this.environmentRepository = environmentRepository;
		this.projectRepository = projectRepository;
		this.duplicateKeyHandler = duplicateKeyHandler;
	}

	public Environment createEnvironment(CreateEnvironmentRequest createEnvironmentRequest, int userId) {
		try {
			if (projectRepository.isFromUser(createEnvironmentRequest.getProjectId(), userId)) {
				return environmentRepository.insert(createEnvironmentRequest,
						createEnvironmentRequest.isForUser() ? userId : null);
			}
		} catch(DuplicateKeyException ex) {
			duplicateKeyHandler.handleDuplicateKey(Arrays.asList(new DuplicateKeyHandlerRequest("environment_project_id_main", new MainEnvironmentAlreadyExistsException())), ex);
		}

		throw new EnvironmentIntegrityException();
	}

	public boolean isFromAccountId(int environmentId, int accountId) {
		return environmentRepository.isFromAccountId(environmentId, accountId);
	}

}
