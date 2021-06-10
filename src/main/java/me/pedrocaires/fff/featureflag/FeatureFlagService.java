package me.pedrocaires.fff.featureflag;

import me.pedrocaires.fff.environment.EnvironmentService;
import me.pedrocaires.fff.exception.alreadyexist.FeatureFlagAlreadyExistException;
import me.pedrocaires.fff.exception.integrity.EnvironmentIntegrityException;
import me.pedrocaires.fff.featureflag.model.CreateFeatureFlagRequest;
import me.pedrocaires.fff.featureflag.model.CreateFeatureFlagResponse;
import me.pedrocaires.fff.featureflag.model.FeatureFlagRequest;
import me.pedrocaires.fff.featureflag.model.FeatureFlagResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class FeatureFlagService {

	private final FeatureFlagRepository featureFlagRepository;

	private final EnvironmentService environmentService;

	private final FeatureFlagMapper featureFlagMapper;

	public FeatureFlagService(FeatureFlagRepository featureFlagRepository, EnvironmentService environmentService,
			FeatureFlagMapper featureFlagMapper) {
		this.featureFlagRepository = featureFlagRepository;
		this.environmentService = environmentService;
		this.featureFlagMapper = featureFlagMapper;
	}

	public CreateFeatureFlagResponse createFeatureFlag(CreateFeatureFlagRequest createFeatureFlagRequest,
			int accountId) {
		// inserir em todos os envs e quando criar um env novo criar tudinho dnv
		try {
			if (environmentService.isFromAccountId(createFeatureFlagRequest.getEnvironmentId(), accountId)) {
				var featureFlag = featureFlagRepository.createFeatureFlag(createFeatureFlagRequest);
				return featureFlagMapper.featureFlagToCreateFeatureFlagResponse(featureFlag);
			}
		}
		catch (DuplicateKeyException ex) {
			throw new FeatureFlagAlreadyExistException();
		}
		throw new EnvironmentIntegrityException();
	}

	@Cacheable(value = "featureFlag")
	public FeatureFlagResponse getFeatureFlag(FeatureFlagRequest featureFlagRequest, int accountId) {
		// colocar cacheable do springfox
		var featureFlag = featureFlagRepository.getFeatureFlag(featureFlagRequest, accountId);
		return featureFlagMapper.featureFlagToFeatureFlagResponse(featureFlag);
	}

}
