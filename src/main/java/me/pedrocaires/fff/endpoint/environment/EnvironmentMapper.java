package me.pedrocaires.fff.endpoint.environment;

import me.pedrocaires.fff.endpoint.environment.model.CreateEnvironmentResponse;
import me.pedrocaires.fff.endpoint.environment.model.Environment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnvironmentMapper {

	CreateEnvironmentResponse environmentToCreateEnvironmentResponse(Environment createEnvironmentRequest);

}
