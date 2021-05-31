package me.pedrocaires.fff.mapper;

import me.pedrocaires.fff.domain.entity.User;
import me.pedrocaires.fff.domain.response.CreateUserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CreateUserResponse userToCreateUserResponse(User user);

}
