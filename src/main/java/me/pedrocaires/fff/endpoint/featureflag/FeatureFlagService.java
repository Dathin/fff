package me.pedrocaires.fff.endpoint.featureflag;

import me.pedrocaires.fff.endpoint.environment.EnvironmentRepository;
import me.pedrocaires.fff.endpoint.environment.EnvironmentService;
import me.pedrocaires.fff.endpoint.featureflag.model.CreateFeatureFlagRequest;
import me.pedrocaires.fff.endpoint.featureflag.model.FeatureFlag;
import me.pedrocaires.fff.endpoint.featureflag.model.FeatureFlagAlreadyExistException;
import me.pedrocaires.fff.endpoint.featureflag.model.FeatureFlagDoesNotExistException;
import me.pedrocaires.fff.endpoint.featureflag.model.FeatureFlagRequest;
import me.pedrocaires.fff.endpoint.environment.model.EnvironmentIntegrityException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class FeatureFlagService {

	private final FeatureFlagRepository featureFlagRepository;

	private final EnvironmentRepository environmentRepository;

	public FeatureFlagService(FeatureFlagRepository featureFlagRepository, EnvironmentService environmentService,
			EnvironmentRepository environmentRepository) {
		this.featureFlagRepository = featureFlagRepository;
		this.environmentRepository = environmentRepository;
	}

	public FeatureFlag createFeatureFlag(CreateFeatureFlagRequest createFeatureFlagRequest, int userId) {
		// inserir em todos os envs e quando criar um env novo criar tudinho dnv
		try {
			if (environmentRepository.isFromAccountId(userId, createFeatureFlagRequest.getEnvironmentId())) {
				return featureFlagRepository.createFeatureFlag(createFeatureFlagRequest);
			}
		}
		catch (DuplicateKeyException ex) {
			throw new FeatureFlagAlreadyExistException();
		}
		throw new EnvironmentIntegrityException();
	}

	@Cacheable(value = "featureFlag")
	public FeatureFlag getFeatureFlag(FeatureFlagRequest featureFlagRequest, int accountId) {
		return featureFlagRepository.getFeatureFlag(featureFlagRequest, accountId)
				.orElseThrow(FeatureFlagDoesNotExistException::new);
	}

}
