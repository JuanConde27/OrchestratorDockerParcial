package co.com.vanegas.microservice.resolveEnigmaApi.api;

import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;

import java.util.*;

import org.junit.jupiter.api.Test; // Usar JUnit 5
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetStepApiControllerIntegrationTest {

    @Autowired
    private GetStepApi api;

    @Test
    public void getStepTest() throws Exception {
        JsonApiBodyRequest body = new JsonApiBodyRequest();
        ResponseEntity<List<JsonApiBodyResponseSuccess>> responseEntity = api.getStep(body);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
