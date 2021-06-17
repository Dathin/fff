package me.pedrocaires.fff.featureflag;

import me.pedrocaires.fff.daoutils.resultsetextractor.FeatureFlagResultSetExtractor;
import me.pedrocaires.fff.featureflag.model.CreateFeatureFlagRequest;
import me.pedrocaires.fff.featureflag.model.FeatureFlag;
import me.pedrocaires.fff.featureflag.model.FeatureFlagRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FeatureFlagRepository {

	private final JdbcTemplate jdbcTemplate;

	private final FeatureFlagResultSetExtractor featureFlagResultSetExtractor;

	public FeatureFlagRepository(JdbcTemplate jdbcTemplate,
			FeatureFlagResultSetExtractor featureFlagResultSetExtractor) {
		this.jdbcTemplate = jdbcTemplate;
		this.featureFlagResultSetExtractor = featureFlagResultSetExtractor;
	}

	public FeatureFlag createFeatureFlag(CreateFeatureFlagRequest createFeatureFlagRequest) {
		return jdbcTemplate.query(
				"INSERT INTO FEATURE_FLAGS (NAME, ENVIRONMENT_ID, VALUE) VALUES (?, ?, ?) RETURNING ID, NAME, ENVIRONMENT_ID, VALUE",
				featureFlagResultSetExtractor.extractObject(), createFeatureFlagRequest.getName(),
				createFeatureFlagRequest.getEnvironmentId(), createFeatureFlagRequest.isValue());
	}

	public Optional<FeatureFlag> getFeatureFlag(FeatureFlagRequest featureFlagRequest, int accountId) {
		return jdbcTemplate.query(
				"SELECT F.ID, F.NAME, F.ENVIRONMENT_ID FROM FEATURE_FLAGS AS F INNER JOIN ENVIRONMENTS AS E ON F.ENVIRONMENT_ID = E.ID INNER JOIN PROJECTS AS P ON E.PROJECT_ID = P.ID WHERE P.ACCOUNT_ID = ? AND ((?::int IS NULL) OR (F.ID = ?)) AND ((?::varchar IS NULL) OR (F.NAME = ?)) LIMIT 1",
				featureFlagResultSetExtractor.extractOptional(), accountId, featureFlagRequest.getId(),
				featureFlagRequest.getId(), featureFlagRequest.getName(), featureFlagRequest.getName());
	}

}
