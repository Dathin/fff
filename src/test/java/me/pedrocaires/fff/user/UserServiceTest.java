package me.pedrocaires.fff.user;

import me.pedrocaires.fff.endpoint.user.*;
import me.pedrocaires.fff.endpoint.user.model.UserAlreadyExistException;
import me.pedrocaires.fff.exception.UserDoesNotExistException;
import me.pedrocaires.fff.endpoint.user.model.CreateUserRequest;
import me.pedrocaires.fff.endpoint.user.model.LoginRequest;
import me.pedrocaires.fff.endpoint.user.model.User;
import me.pedrocaires.fff.endpoint.user.model.UserToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	PodamFactory podamFactory = new PodamFactoryImpl();

	@Mock
	UserRepository userRepository;

	@Mock
	PasswordEncoder passwordEncoder;

	@Mock
	JwtService jwtService;

	@Spy
	UserMapper userMapper = new UserMapperImpl();

	@Mock
	CreateUserRequest createUserRequest;

	@Mock
	SecurityContext securityContext;

	@Mock
	Authentication authentication;

	@InjectMocks
	UserService userService;

	@BeforeEach
	void setUp() {
		SecurityContextHolder.clearContext();
	}

	@Test
	void shouldInsertNewUserAtAuthenticatedUserAccount() {
		var accountId = 1;
		var userToken = podamFactory.manufacturePojo(UserToken.class);
		userToken.setAccountId(accountId);
		userService.setAuthenticatedUser(userToken);
		var user = podamFactory.manufacturePojo(User.class);
		when(userRepository.insert(createUserRequest)).thenReturn(user);

		userService.createUser(createUserRequest);

		verify(userRepository).insert(createUserRequest);
		verify(userMapper).userToCreateUserResponse(user);
	}

	@Test
	void shouldInsertNewUserIfItIsAccountsFirstUser() {
		mockAuthenticationWithoutUser();

		userService.createUser(createUserRequest);

		verify(userRepository).insert(createUserRequest);
	}

	@Test
	void shouldAlwaysEncryptPassword() {
		var encryptedPassword = "myEncryptedPassword";
		when(passwordEncoder.encode(createUserRequest.getPassword())).thenReturn(encryptedPassword);
		mockAuthenticationWithoutUser();

		verify(createUserRequest).setPassword(encryptedPassword);
	}

	@Test
	void shouldThrowUserAlreadyExistsException() {
		mockAuthenticationWithoutUser();
		when(userRepository.insert(createUserRequest)).thenThrow(DuplicateKeyException.class);

		assertThrows(UserAlreadyExistException.class, () -> userService.createUser(createUserRequest));
	}

	@Test
	void shouldThrowAccountDoesNotExistException() {
		mockAuthenticationWithoutUser();
		when(userRepository.insert(createUserRequest)).thenThrow(DataIntegrityViolationException.class);
	}

	@Test
	void shouldLogin() {
		var user = podamFactory.manufacturePojo(User.class);
		var optionalUser = Optional.of(user);
		var loginRequest = podamFactory.manufacturePojo(LoginRequest.class);
		when(userRepository.getPasswordToLogin(loginRequest)).thenReturn(optionalUser);
		when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(true);
		var token = "myToken";
		when(jwtService.issueToken(user)).thenReturn(token);

		userService.login(loginRequest);

		verify(jwtService).issueToken(user);
		verify(userMapper).tokenToLoginResponse(token);
	}

	@Test
	void shouldThrowUserDoesNotExistExceptionWhenInvalidNameAndEmail() {
		User user = null;
		var optionalUser = Optional.ofNullable(user);
		var loginRequest = podamFactory.manufacturePojo(LoginRequest.class);
		when(userRepository.getPasswordToLogin(loginRequest)).thenReturn(optionalUser);

		assertThrows(UserDoesNotExistException.class, () -> userService.login(loginRequest));
	}

	@Test
	void shouldThrowUserDoesNotExistExceptionWhenInvalidPassword() {
		User user = podamFactory.manufacturePojo(User.class);
		var optionalUser = Optional.ofNullable(user);
		var loginRequest = podamFactory.manufacturePojo(LoginRequest.class);
		when(userRepository.getPasswordToLogin(loginRequest)).thenReturn(optionalUser);
		when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(false);

		assertThrows(UserDoesNotExistException.class, () -> userService.login(loginRequest));
	}

	@Test
	void shouldGetAuthenticatedUser() {
		var userToken = podamFactory.manufacturePojo(UserToken.class);
		userService.setAuthenticatedUser(userToken);

		var authenticatedUser = userService.getAuthenticatedUser();

		assertEquals(userToken, authenticatedUser.get());
	}

	@Test
	void shouldGetEmptyWhenNoAuthenticatedUser() {
		mockAuthenticationWithoutUser();

		var authenticatedUser = userService.getAuthenticatedUser();

		assertTrue(authenticatedUser.isEmpty());
	}

	private void mockAuthenticationWithoutUser() {
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
	}

}