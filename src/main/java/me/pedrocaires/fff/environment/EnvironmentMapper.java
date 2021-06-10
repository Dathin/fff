package me.pedrocaires.fff.environment;

import me.pedrocaires.fff.environment.model.CreateEnvironmentRequest;
import me.pedrocaires.fff.environment.model.CreateEnvironmentResponse;
import me.pedrocaires.fff.environment.model.Environment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnvironmentMapper {

	CreateEnvironmentResponse environmentToCreateEnvironmentResponse(Environment createEnvironmentRequest);

}
