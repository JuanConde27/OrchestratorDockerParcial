package co.com.vanegas.microservice.resolveEnigmaApi.api;

import co.com.vanegas.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation; // Usando la nueva anotación
import io.swagger.v3.oas.annotations.Parameter; // Usando la nueva anotación
import io.swagger.v3.oas.annotations.responses.ApiResponse; // Usando la nueva anotación
import io.swagger.v3.oas.annotations.responses.ApiResponses; // Usando la nueva anotación
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GetStepApiController implements GetStepApi {

    private static final Logger log = LoggerFactory.getLogger(GetStepApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public GetStepApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Operation(summary = "Get one enigma step", description = "Retrieve a step of the enigma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of the step"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<List<JsonApiBodyResponseSuccess>> getStep(
            @Parameter(description = "Request body for getting the enigma step", required = true)
            @Valid @RequestBody JsonApiBodyRequest body) {
        if (body == null || body.getData() == null || body.getData().isEmpty() || body.getData().get(0).getHeader() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String accept = request.getHeader("Accept");

        GetEnigmaStepResponse getEnigmaStepResponse = new GetEnigmaStepResponse();
        getEnigmaStepResponse.answer("Abrir la nevera");
        getEnigmaStepResponse.header(body.getData().get(0).getHeader());

        JsonApiBodyResponseSuccess responseSuccess = new JsonApiBodyResponseSuccess();
        responseSuccess.addDataItem(getEnigmaStepResponse);

        List<JsonApiBodyResponseSuccess> response = List.of(responseSuccess);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
