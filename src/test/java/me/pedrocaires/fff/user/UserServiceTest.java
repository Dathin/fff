package me.pedrocaires.fff.user;

import me.pedrocaires.fff.exception.AccountDoesNotExistException;
import me.pedrocaires.fff.exception.InvalidCreateUserOnAccount;
import me.pedrocaires.fff.exception.UserAlreadyExistException;
import me.pedrocaires.fff.exception.UserDoesNotExistException;
import me.pedrocaires.fff.user.model.CreateUserRequest;
import me.pedrocaires.fff.user.model.LoginRequest;
import me.pedrocaires.fff.user.model.User;
import me.pedrocaires.fff.user.model.UserToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtService jwtService;

    @Mock
    UserMapper userMapper;

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
        var userToken = new UserToken();
        userToken.setAccountId(accountId);
        userService.setAuthenticatedUser(userToken);
        var user = new User();
        when(userRepository.insert(createUserRequest)).thenReturn(user);

        userService.createUser(createUserRequest);

        verify(createUserRequest).setAccountId(accountId);
        verify(userRepository).insert(createUserRequest);
        verify(userMapper).userToCreateUserResponse(user);
    }

    @Test
    void shouldInsertNewUserIfItIsAccountsFirstUser() {
        mockAuthenticationWithoutUser();
        when(userRepository.countByAccountId(createUserRequest.getAccountId())).thenReturn(0);

        userService.createUser(createUserRequest);

        verify(userRepository).insert(createUserRequest);
    }

    @Test
    void shouldThrowInvalidCreateUserOnAccountIfNotFirstAccountUserOrAuthenticated() {
        mockAuthenticationWithoutUser();
        when(userRepository.countByAccountId(createUserRequest.getAccountId())).thenReturn(1);

        assertThrows(InvalidCreateUserOnAccount.class, () -> userService.createUser(createUserRequest));
    }

    @Test
    void shouldAlwaysEncryptPassword() {
        var encryptedPassword = "myEncryptedPassword";
        when(passwordEncoder.encode(createUserRequest.getPassword())).thenReturn(encryptedPassword);
        mockAuthenticationWithoutUser();
        when(userRepository.countByAccountId(createUserRequest.getAccountId())).thenReturn(1);

        assertThrows(InvalidCreateUserOnAccount.class, () -> userService.createUser(createUserRequest));
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

        assertThrows(AccountDoesNotExistException.class, () -> userService.createUser(createUserRequest));
    }

    @Test
    void shouldLogin() {
        var user = new User();
        var optionalUser = Optional.of(user);
        var loginRequest = new LoginRequest();
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
        var loginRequest = new LoginRequest();
        when(userRepository.getPasswordToLogin(loginRequest)).thenReturn(optionalUser);

        assertThrows(UserDoesNotExistException.class, () -> userService.login(loginRequest));
    }

    @Test
    void shouldThrowUserDoesNotExistExceptionWhenInvalidPassword() {
        User user = new User();
        var optionalUser = Optional.ofNullable(user);
        var loginRequest = new LoginRequest();
        when(userRepository.getPasswordToLogin(loginRequest)).thenReturn(optionalUser);
        when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(false);

        assertThrows(UserDoesNotExistException.class, () -> userService.login(loginRequest));
    }

    @Test
    void shouldGetAuthenticatedUser() {
        var userToken = new UserToken();
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

    @Test
    void shouldSetAuthenticatedUser() {
        var userToken = new UserToken();

        userService.setAuthenticatedUser(userToken);
    }

    private void mockAuthenticationWithoutUser(){
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }
}