package co.com.asprilla.tallerreactiva;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orchestrator")
public class OrchestrationController {

    private final OrchestrationService orchestrationService;

    public OrchestrationController(OrchestrationService orchestrationService) {
        this.orchestrationService = orchestrationService;
    }

    @GetMapping("/parallel-responses")
    public Mono<ResponseEntity<String>> getCombinedResponses() {
        return orchestrationService.orchestrateResponses();
    }
}
