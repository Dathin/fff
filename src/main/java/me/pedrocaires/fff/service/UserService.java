package me.pedrocaires.fff.service;

import me.pedrocaires.fff.domain.entity.User;
import me.pedrocaires.fff.domain.request.CreateUserRequest;
import me.pedrocaires.fff.domain.request.LoginRequest;
import me.pedrocaires.fff.domain.response.CreateUserResponse;
import me.pedrocaires.fff.domain.response.LoginResponse;
import me.pedrocaires.fff.exception.UserDoesNotExistException;
import me.pedrocaires.fff.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
        // TODO: Verificar se esse cara deveria estar sendo inserido na conta
        //Verificar se a conta existe
        //Verificar quando o cara cria uma conta nova ou vem de uma conta existente
        //Verificar quand bate na unique constraint
        //Padrão de insert pra esse cara e pro de account
        //Refatorar pastas
        // Auth
        var bcryptedPassword =  passwordEncoder.encode(createUserRequest.getPassword());
        createUserRequest.setPassword(bcryptedPassword);
        var insertedId = userRepository.insert(createUserRequest);
        var createUserResponse = new CreateUserResponse();
        createUserResponse.setId(insertedId);
        createUserResponse.setName(createUserRequest.getName());
        createUserResponse.setAccountId(createUserRequest.getAccountId());
        return createUserResponse;
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

    public User getSecurityContextHolder(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public void setSecurityContextHolder(User user){
        Authentication authentication = new PreAuthenticatedAuthenticationToken(
                user, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
