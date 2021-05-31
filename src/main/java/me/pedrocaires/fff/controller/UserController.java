package me.pedrocaires.fff.controller;

import me.pedrocaires.fff.domain.request.CreateUserRequest;
import me.pedrocaires.fff.domain.request.LoginRequest;
import me.pedrocaires.fff.domain.response.CreateUserResponse;
import me.pedrocaires.fff.domain.response.LoginResponse;
import me.pedrocaires.fff.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest){
        var user =  userService.getSecurityContextHolder();
        return ResponseEntity.ok(userService.createUser(createUserRequest));
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userService.login(loginRequest));
    }

}
