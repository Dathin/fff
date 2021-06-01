package me.pedrocaires.fff.user;

import me.pedrocaires.fff.account.model.CreateAccountRequest;
import me.pedrocaires.fff.account.model.CreateAccountResponse;
import me.pedrocaires.fff.user.model.CreateUserRequest;
import me.pedrocaires.fff.user.model.CreateUserResponse;
import me.pedrocaires.fff.user.model.LoginRequest;
import me.pedrocaires.fff.user.model.LoginResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    void shouldCreateAccount() {
        var createUserRequest = new CreateUserRequest();
        var createUserResponse = new CreateUserResponse();
        when(userService.createUser(createUserRequest)).thenReturn(createUserResponse);

        var createdUserResponseEntity = userController.createUser(createUserRequest);

        verify(userService).createUser(createUserRequest);
        assertEquals(HttpStatus.OK, createdUserResponseEntity.getStatusCode());
        assertEquals(createUserResponse, createdUserResponseEntity.getBody());
    }

    @Test
    void shouldLogin() {
        var loginRequest = new LoginRequest();
        var loginResponse = new LoginResponse();
        when(userService.login(loginRequest)).thenReturn(loginResponse);

        var createdUserResponseEntity = userController.login(loginRequest);

        verify(userService).login(loginRequest);
        assertEquals(HttpStatus.OK, createdUserResponseEntity.getStatusCode());
        assertEquals(loginResponse, createdUserResponseEntity.getBody());
    }

}