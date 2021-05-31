package me.pedrocaires.fff.service;

import me.pedrocaires.fff.domain.entity.User;
import me.pedrocaires.fff.domain.request.CreateUserRequest;
import me.pedrocaires.fff.domain.request.LoginRequest;
import me.pedrocaires.fff.domain.response.CreateUserResponse;
import me.pedrocaires.fff.domain.response.LoginResponse;
import me.pedrocaires.fff.exception.AccountDoesNotExistException;
import me.pedrocaires.fff.exception.InvalidCreateUserOnAccount;
import me.pedrocaires.fff.exception.UserAlreadyExistException;
import me.pedrocaires.fff.exception.UserDoesNotExistException;
import me.pedrocaires.fff.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
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


    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        var bcryptedPassword =  passwordEncoder.encode(createUserRequest.getPassword());
        createUserRequest.setPassword(bcryptedPassword);
        var optionalAuthenticatedUser = getAuthenticatedUser();
        try {
                if (optionalAuthenticatedUser.isPresent()){
                var authenticatedUser = optionalAuthenticatedUser.get();
                createUserRequest.setAccountId(authenticatedUser.getAccountId());
                var user = userRepository.insert(createUserRequest);
                var createUserResponse = new CreateUserResponse();
                createUserResponse.setId(user.getId());
                createUserResponse.setName(user.getName());
                createUserResponse.setAccountId(user.getAccountId());
                return createUserResponse;
                }
                if (userRepository.countByAccountId(createUserRequest.getAccountId()) > 1){
                    var user = userRepository.insert(createUserRequest);
                    var createUserResponse = new CreateUserResponse();
                    createUserResponse.setId(user.getId());
                    createUserResponse.setName(user.getName());
                    createUserResponse.setAccountId(user.getAccountId());
                    return createUserResponse;
                }
                throw new InvalidCreateUserOnAccount();
            } catch (DuplicateKeyException ex){
                throw new UserAlreadyExistException();
            } catch (DataIntegrityViolationException ex){
                throw new AccountDoesNotExistException();
            }
    }

    public LoginResponse login(LoginRequest loginRequest){
        var optionalUser = userRepository.getPasswordToLogin(loginRequest);
        optionalUser.orElseThrow(UserDoesNotExistException::new);
        var user = optionalUser.get();
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            var loginResponse = new LoginResponse();
            loginResponse.setToken(jwtService.issueToken(user));
            return loginResponse;
        }
        throw new UserDoesNotExistException();
    }

    public Optional<User> getAuthenticatedUser(){
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof User) {
            return Optional.of((User) user);
        }
        return Optional.empty();
    }

    public void setAuthenticatedUser(User user){
        Authentication authentication = new PreAuthenticatedAuthenticationToken(
                user, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
