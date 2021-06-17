package me.pedrocaires.fff.user;

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
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	PodamFactory podamFactory = new PodamFactoryImpl();

	@Mock
	UserService userService;

	@InjectMocks
	UserController userController;

	@Test
	void shouldCreateAccount() {
		var createUserRequest = podamFactory.manufacturePojo(CreateUserRequest.class);
		var createUserResponse = podamFactory.manufacturePojo(CreateUserResponse.class);
		// when(userService.createUser(createUserRequest)).thenReturn(createUserResponse);

		var createdUserResponseEntity = userController.createUser(createUserRequest);

		verify(userService).createUser(createUserRequest);
		assertEquals(HttpStatus.OK, createdUserResponseEntity.getStatusCode());
		assertEquals(createUserResponse, createdUserResponseEntity.getBody());
	}

	@Test
	void shouldLogin() {
		var loginRequest = podamFactory.manufacturePojo(LoginRequest.class);
		var loginResponse = podamFactory.manufacturePojo(LoginResponse.class);
		// when(userService.login(loginRequest)).thenReturn(loginResponse);

		var createdUserResponseEntity = userController.login(loginRequest);

		verify(userService).login(loginRequest);
		assertEquals(HttpStatus.OK, createdUserResponseEntity.getStatusCode());
		assertEquals(loginResponse, createdUserResponseEntity.getBody());
	}

}