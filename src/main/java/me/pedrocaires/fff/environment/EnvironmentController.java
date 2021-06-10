package me.pedrocaires.fff.environment;

import me.pedrocaires.fff.environment.model.CreateEnvironmentRequest;
import me.pedrocaires.fff.environment.model.CreateEnvironmentResponse;
import me.pedrocaires.fff.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/environment")
public class EnvironmentController {

	private final EnvironmentService environmentService;

	private final UserService userService;

	public EnvironmentController(EnvironmentService environmentService, UserService userService) {
		this.environmentService = environmentService;
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<CreateEnvironmentResponse> createEnvironment(
			@RequestBody CreateEnvironmentRequest createEnvironmentRequest) {
		var userToken = userService.getOrThrowAuthenticatedUser();
		return ResponseEntity.ok(environmentService.createEnvironment(createEnvironmentRequest, userToken));
	}

	// @GetMapping
	// public ResponseEntity<List<EnvironmentResponse>> getEnvironments() {
	// return ResponseEntity.ok(environmentService.getEnvironments());
	// }

}
