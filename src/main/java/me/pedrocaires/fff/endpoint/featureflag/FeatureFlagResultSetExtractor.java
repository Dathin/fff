package me.pedrocaires.fff.endpoint.featureflag;

import me.pedrocaires.fff.daoutils.EntityResultSetExtractor;
import me.pedrocaires.fff.endpoint.featureflag.model.FeatureFlag;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FeatureFlagResultSetExtractor extends EntityResultSetExtractor<FeatureFlag> {

	@Override
	public FeatureFlag rawExtraction(ResultSet resultSet) throws SQLException {
		var featureFlag = new FeatureFlag();
		featureFlag.setId(resultSet.getInt("ID"));
		featureFlag.setName(resultSet.getString("NAME"));
		featureFlag.setEnvironmentId(resultSet.getInt("ENVIRONMENT_ID"));
		return featureFlag;
	}

}
