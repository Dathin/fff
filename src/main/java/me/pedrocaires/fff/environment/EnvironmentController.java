package me.pedrocaires.fff.environment;

import me.pedrocaires.fff.environment.model.CreateEnvironmentRequest;
import me.pedrocaires.fff.environment.model.CreateEnvironmentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/environment")
public class EnvironmentController {

    private final EnvironmentService environmentService;

    public EnvironmentController(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    @PostMapping
    public ResponseEntity<CreateEnvironmentResponse> createEnvironment(@RequestBody CreateEnvironmentRequest createEnvironmentRequest) {
        return ResponseEntity.ok(environmentService.createEnvironment(createEnvironmentRequest));
    }

//    @GetMapping
//    public ResponseEntity<List<EnvironmentResponse>> getEnvironments() {
//        return ResponseEntity.ok(environmentService.getEnvironments());
//    }

}
