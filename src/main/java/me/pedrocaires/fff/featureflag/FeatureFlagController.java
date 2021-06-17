package me.pedrocaires.fff.featureflag;

import me.pedrocaires.fff.featureflag.model.CreateFeatureFlagRequest;
import me.pedrocaires.fff.featureflag.model.CreateFeatureFlagResponse;
import me.pedrocaires.fff.featureflag.model.FeatureFlagRequest;
import me.pedrocaires.fff.featureflag.model.FeatureFlagResponse;
import me.pedrocaires.fff.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.Cacheable;

import javax.validation.Valid;

@RestController
@RequestMapping("/featureflag")
public class FeatureFlagController {

	private final FeatureFlagService featureFlagService;

	private final UserService userService;

	public FeatureFlagController(FeatureFlagService featureFlagService, UserService userService) {
		this.featureFlagService = featureFlagService;
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<CreateFeatureFlagResponse> createFeatureFlag(
			@Valid @RequestBody CreateFeatureFlagRequest createFeatureFlagRequest) {
		var accountId = userService.getOrThrowAuthenticatedUser().getAccountId();
		return ResponseEntity.ok(featureFlagService.createFeatureFlag(createFeatureFlagRequest, accountId));
	}

	@GetMapping
	public ResponseEntity<FeatureFlagResponse> getFeatureFlag(@Valid FeatureFlagRequest featureFlagRequest) {
		var accountId = userService.getOrThrowAuthenticatedUser().getAccountId();
		var a = featureFlagService.getFeatureFlag(featureFlagRequest, accountId);
		return ResponseEntity.ok(a);
	}

}
