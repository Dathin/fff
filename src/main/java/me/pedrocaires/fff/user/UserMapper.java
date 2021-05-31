package me.pedrocaires.fff.user;

import me.pedrocaires.fff.user.model.CreateUserResponse;
import me.pedrocaires.fff.user.model.LoginResponse;
import me.pedrocaires.fff.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CreateUserResponse userToCreateUserResponse(User user);

    LoginResponse tokenToLoginResponse(String token);

}
