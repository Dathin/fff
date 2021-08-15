package me.pedrocaires.fff.endpoint.featureflag;

import me.pedrocaires.fff.endpoint.featureflag.model.CreateFeatureFlagResponse;
import me.pedrocaires.fff.endpoint.featureflag.model.FeatureFlag;
import me.pedrocaires.fff.endpoint.featureflag.model.FeatureFlagResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeatureFlagMapper {

	CreateFeatureFlagResponse featureFlagToCreateFeatureFlagResponse(FeatureFlag featureFlag);

	FeatureFlagResponse featureFlagToFeatureFlagResponse(FeatureFlag featureFlag);

}
