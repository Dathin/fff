package me.pedrocaires.fff.endpoint.user;

import me.pedrocaires.fff.endpoint.user.model.*;
import me.pedrocaires.fff.exception.*;
import me.pedrocaires.fff.exception.UserDoesNotExistException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtService;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	public User createUser(CreateUserRequest createUserRequest) {
		encryptPassword(createUserRequest);
		try {
			return userRepository.insert(createUserRequest);
		}
		catch (DuplicateKeyException ex) {
			throw new UserAlreadyExistException();
		}
	}

	private void encryptPassword(CreateUserRequest createUserRequest) {
		createUserRequest.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
	}

	public String login(LoginRequest loginRequest) {
		var optionalUser = userRepository.getPasswordToLogin(loginRequest);
		var user = optionalUser.orElseThrow(UserDoesNotExistException::new);
		return checkPassword(user, loginRequest);
	}

	private String checkPassword(User user, LoginRequest loginRequest) {
		if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
			return jwtService.issueToken(user);
		}
		throw new UserDoesNotExistException();
	}

	public Optional<UserToken> getAuthenticatedUser() {
		var userToken = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userToken instanceof UserToken) {
			return Optional.of((UserToken) userToken);
		}
		return Optional.empty();
	}

	public UserToken getOrThrowAuthenticatedUser() {
		return getAuthenticatedUser().orElseThrow(UnauthorizedException::new);
	}

	public void setAuthenticatedUser(UserToken userToken) {
		Authentication authentication = new PreAuthenticatedAuthenticationToken(userToken, null,
				Collections.emptyList());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
