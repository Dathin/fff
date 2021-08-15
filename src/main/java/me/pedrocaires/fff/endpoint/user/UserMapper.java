package me.pedrocaires.fff.endpoint.user;

import me.pedrocaires.fff.endpoint.user.model.CreateUserResponse;
import me.pedrocaires.fff.endpoint.user.model.User;
import me.pedrocaires.fff.endpoint.user.model.LoginResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

	CreateUserResponse userToCreateUserResponse(User user);

	LoginResponse tokenToLoginResponse(String token);

}
