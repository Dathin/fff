package me.pedrocaires.fff.featureflag;

import me.pedrocaires.fff.featureflag.model.CreateFeatureFlagResponse;
import me.pedrocaires.fff.featureflag.model.FeatureFlag;
import me.pedrocaires.fff.featureflag.model.FeatureFlagResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeatureFlagMapper {

	CreateFeatureFlagResponse featureFlagToCreateFeatureFlagResponse(FeatureFlag featureFlag);

	FeatureFlagResponse featureFlagToFeatureFlagResponse(FeatureFlag featureFlag);

}
