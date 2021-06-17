package me.pedrocaires.fff.featureflag;

import me.pedrocaires.fff.featureflag.model.CreateFeatureFlagRequest;
import me.pedrocaires.fff.featureflag.model.CreateFeatureFlagResponse;
import me.pedrocaires.fff.featureflag.model.FeatureFlagRequest;
import me.pedrocaires.fff.featureflag.model.FeatureFlagResponse;
import me.pedrocaires.fff.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/featureflag")
public class FeatureFlagController {

	private final FeatureFlagService featureFlagService;

	private final UserService userService;

	private final FeatureFlagMapper featureFlagMapper;

	public FeatureFlagController(FeatureFlagService featureFlagService, UserService userService,
			FeatureFlagMapper featureFlagMapper) {
		this.featureFlagService = featureFlagService;
		this.userService = userService;
		this.featureFlagMapper = featureFlagMapper;
	}

	@PostMapping
	public ResponseEntity<CreateFeatureFlagResponse> createFeatureFlag(
			@Valid @RequestBody CreateFeatureFlagRequest createFeatureFlagRequest) {
		var accountId = userService.getOrThrowAuthenticatedUser().getAccountId();
		var createdFeatureFlag = featureFlagService.createFeatureFlag(createFeatureFlagRequest, accountId);
		return ResponseEntity.ok(featureFlagMapper.featureFlagToCreateFeatureFlagResponse(createdFeatureFlag));
	}

	@GetMapping
	public ResponseEntity<FeatureFlagResponse> getFeatureFlag(@Valid FeatureFlagRequest featureFlagRequest) {
		var accountId = userService.getOrThrowAuthenticatedUser().getAccountId();
		var featureFlag = featureFlagService.getFeatureFlag(featureFlagRequest, accountId);
		return ResponseEntity.ok(featureFlagMapper.featureFlagToFeatureFlagResponse(featureFlag));
	}

}
