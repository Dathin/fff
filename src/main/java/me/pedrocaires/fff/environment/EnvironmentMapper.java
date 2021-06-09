package me.pedrocaires.fff.environment;

import me.pedrocaires.fff.environment.model.CreateEnvironmentRequest;
import me.pedrocaires.fff.environment.model.CreateEnvironmentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnvironmentMapper {

    CreateEnvironmentResponse environmentTocreateEnvironmentResponse(CreateEnvironmentRequest createEnvironmentRequest);

}
