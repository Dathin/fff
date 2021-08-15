package me.pedrocaires.fff.endpoint.user;

import me.pedrocaires.fff.endpoint.user.model.CreateUserRequest;
import me.pedrocaires.fff.endpoint.user.model.CreateUserResponse;
import me.pedrocaires.fff.endpoint.user.model.LoginRequest;
import me.pedrocaires.fff.endpoint.user.model.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	private final UserMapper userMapper;

	public UserController(UserService userService, UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@PostMapping
	public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
		var createdUser = userService.createUser(createUserRequest);
		return ResponseEntity.ok(userMapper.userToCreateUserResponse(createdUser));
	}

	@PostMapping("login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
		var token = userService.login(loginRequest);
		return ResponseEntity.ok(userMapper.tokenToLoginResponse(token));
	}

}
