package me.pedrocaires.fff.endpoint.project;

import me.pedrocaires.fff.endpoint.account.AccountRepository;
import me.pedrocaires.fff.endpoint.project.models.CreateProjectRequest;
import me.pedrocaires.fff.endpoint.project.models.Project;
import me.pedrocaires.fff.endpoint.project.models.ProjectIntegrityException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

	private final ProjectRepository projectRepository;

	private final AccountRepository accountRepository;

	public ProjectService(ProjectRepository projectRepository, AccountRepository accountRepository) {
		this.projectRepository = projectRepository;
		this.accountRepository = accountRepository;
	}

	public List<Project> getProjectsFromAccountId(Integer accountId, int userId) {
		return projectRepository.getProjectsByAccountId(accountId, userId);
	}

	public Project createProjectForUser(CreateProjectRequest createProjectRequest, int userId) {
		if (accountRepository.isFromAccount(createProjectRequest.getAccountId(), userId)) {
			return projectRepository.insert(createProjectRequest);
		}
		throw new ProjectIntegrityException();
	}

}
