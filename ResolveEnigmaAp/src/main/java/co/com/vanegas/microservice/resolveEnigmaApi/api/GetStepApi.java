package co.com.vanegas.microservice.resolveEnigmaApi.api;

import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyResponseErrors;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/getStep")
public interface GetStepApi {

    @Operation(summary = "Get one enigma step API", description = "Get one enigma step API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "search results matching criteria", content = @Content(schema = @Schema(implementation = JsonApiBodyResponseSuccess.class))),
            @ApiResponse(responseCode = "424", description = "bad input parameter", content = @Content(schema = @Schema(implementation = JsonApiBodyResponseErrors.class)))
    })
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    ResponseEntity<List<JsonApiBodyResponseSuccess>> getStep(
            @Parameter(description = "request body get enigma step", required = true)
            @Valid @RequestBody JsonApiBodyRequest body);
}
