package me.pedrocaires.fff.endpoint.environment;

import me.pedrocaires.fff.endpoint.environment.model.CreateEnvironmentRequest;
import me.pedrocaires.fff.endpoint.environment.model.CreateEnvironmentResponse;
import me.pedrocaires.fff.endpoint.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/environment")
public class EnvironmentController {

	private final EnvironmentService environmentService;

	private final UserService userService;

	private final EnvironmentMapper environmentMapper;

	public EnvironmentController(EnvironmentService environmentService, UserService userService,
			EnvironmentMapper environmentMapper) {
		this.environmentService = environmentService;
		this.userService = userService;
		this.environmentMapper = environmentMapper;
	}

	@PostMapping
	public ResponseEntity<CreateEnvironmentResponse> createEnvironment(
			@Valid @RequestBody CreateEnvironmentRequest createEnvironmentRequest) {
		var userToken = userService.getOrThrowAuthenticatedUser();
		var createdEnvironment = environmentService.createEnvironment(createEnvironmentRequest, userToken);
		return ResponseEntity.ok(environmentMapper.environmentToCreateEnvironmentResponse(createdEnvironment));
	}

	// @GetMapping
	// public ResponseEntity<List<EnvironmentResponse>> getEnvironments() {
	// return ResponseEntity.ok(environmentService.getEnvironments());
	// }

}
