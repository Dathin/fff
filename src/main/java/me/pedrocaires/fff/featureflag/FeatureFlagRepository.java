package me.pedrocaires.fff.featureflag;

import me.pedrocaires.fff.featureflag.model.CreateFeatureFlagRequest;
import me.pedrocaires.fff.featureflag.model.FeatureFlag;
import me.pedrocaires.fff.featureflag.model.FeatureFlagRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FeatureFlagRepository {

	private final JdbcTemplate jdbcTemplate;

	public FeatureFlagRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public FeatureFlag createFeatureFlag(CreateFeatureFlagRequest createFeatureFlagRequest) {
		return jdbcTemplate.query(
				"INSERT INTO FEATURE_FLAGS (NAME, ENVIRONMENT_ID, VALUE) VALUES (?, ?, ?) RETURNING ID, NAME, ENVIRONMENT_ID, VALUE",
				resultSet -> {
					resultSet.next();
					var featureFlag = new FeatureFlag();
					featureFlag.setId(resultSet.getInt("ID"));
					featureFlag.setName(resultSet.getString("NAME"));
					featureFlag.setEnvironmentId(resultSet.getInt("ENVIRONMENT_ID"));
					featureFlag.setValue(resultSet.getBoolean("VALUE"));
					return featureFlag;
				}, createFeatureFlagRequest.getName(), createFeatureFlagRequest.getEnvironmentId(),
				createFeatureFlagRequest.isValue());
	}

	public FeatureFlag getFeatureFlag(FeatureFlagRequest featureFlagRequest, int accountId) {
		// fazer o result set extractor
		// não poderia ter chegado aqui se o id e name foram nulls
		// fazer o validator
		// response do swagger
		// corrigir os unit tests
		// mapper no lugar certo
		// problema de auth
		return jdbcTemplate.query(
				"SELECT F.ID, F.NAME, F.ENVIRONMENT_ID FROM FEATURE_FLAGS AS F INNER JOIN ENVIRONMENTS AS E ON F.ENVIRONMENT_ID = E.ID INNER JOIN PROJECTS AS P ON E.PROJECT_ID = P.ID WHERE P.ACCOUNT_ID = ? AND (CAST(? AS INT) IS NULL OR F.ID = ?) AND (CAST(? AS VARCHAR) IS NULL OR F.NAME = ?) LIMIT 1",
				resultSet -> {
					var next = resultSet.next();
					var featureFlag = new FeatureFlag();
					featureFlag.setId(resultSet.getInt("ID"));
					featureFlag.setName(resultSet.getString("NAME"));
					featureFlag.setEnvironmentId(resultSet.getInt("ENVIRONMENT_ID"));
					return featureFlag;
				}, accountId, featureFlagRequest.getId(), featureFlagRequest.getId(), featureFlagRequest.getName(),
				featureFlagRequest.getName());
	}

}
