package co.com.asprilla.tallerreactiva;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import io.github.resilience4j.retry.annotation.Retry;
import java.time.Duration;

@Service
public class OrchestrationService {

    private static final Logger logger = LoggerFactory.getLogger(OrchestrationService.class);
    private final WebClient webClient;

    public OrchestrationService(WebClient webClient) {
        this.webClient = webClient;
    }

    @CircuitBreaker(name = "orchestrationService", fallbackMethod = "fallback")
    @Retry(name = "orchestrationService")
    public Mono<ResponseEntity<String>> orchestrateResponses() {
        // Cuerpo de las peticiones para cada paso
        String requestBody1 = prepareBody(1);
        String requestBody2 = prepareBody(2);
        String requestBody3 = prepareBody(3);

        // Servicio 1
        Mono<String> service1 = callService(requestBody1, "http://host.docker.internal:8080/getStep", "Service 1");

        // Servicio 2
        Mono<String> service2 = callService(requestBody2, "http://host.docker.internal:8081/getStep", "Service 2");

        // Servicio 3
        Mono<String> service3 = callService(requestBody3, "http://host.docker.internal:8082/getStep", "Service 3");

        // Combinar las respuestas de los tres servicios
        return Mono.zip(service1, service2, service3)
                .flatMap(tuple -> {
                    // Construcción de la respuesta final en formato JSON
                    String finalResponse = String.format(
                            "{\"data\": [{\"header\": {\"id\": \"12345\", \"type\": \"TestGiraffeRefrigerator\"}, \"answer\": \"Step1: %s - Step2: %s - Step3: %s\"}]}",
                            tuple.getT1(), tuple.getT2(), tuple.getT3()
                    );
                    logger.info("Orquestación completada. Respuesta final: {}", finalResponse);

                    // Llamar al webhook y luego retornar la respuesta final
                    return callWebhook()
                            .thenReturn(ResponseEntity.ok(finalResponse));
                });
    }

    private Mono<String> callService(String requestBody, String uri, String serviceName) {
        logger.info("Attempting to call {}", serviceName); // Log del intento

        return webClient.post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(HttpStatusCode::isError, this::handleWebClientError)
                .bodyToMono(String.class)
                .map(this::extractAnswer)
                .doOnError(e -> logger.error("Error in {}: {}", serviceName, e.getMessage()))
                .doOnSuccess(response -> logger.info("{} success response: {}", serviceName, response))
                .retryWhen(reactor.util.retry.Retry.backoff(3, Duration.ofSeconds(1)) // Configurar 3 reintentos
                        .doBeforeRetry(retrySignal -> logger.warn("Retrying call to {} due to error: {}. Attempt: {}",
                                serviceName, retrySignal.failure().getMessage(), retrySignal.totalRetriesInARow() + 1)))
                .onErrorResume(e -> {
                    logger.error("Final error in {}: {}", serviceName, e.getMessage());
                    return Mono.just(serviceName + " unavailable"); // Manejo de error
                });
    }

    @CircuitBreaker(name = "webhookService", fallbackMethod = "fallback")
    public Mono<Void> callWebhook() {
        logger.info("Llamando al webhook en {}", System.currentTimeMillis());
        return webClient.post()
                .uri("http://host.docker.internal:8085/webhook")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue("{}") // Enviar un cuerpo vacío o personalizado si es necesario
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> logger.info("Webhook llamado exitosamente: {}", response))
                .doOnError(e -> logger.error("Error al llamar al webhook: {}", e.getMessage()))
                .then(); // Convertir a Mono<Void>
    }


    // Método fallback que se ejecuta cuando se activa el Circuit Breaker
    public Mono<ResponseEntity<String>> fallback(Throwable throwable) {
        logger.error("Fallback method activated due to: {}", throwable.getMessage());
        String errorMessage = "{\"error\": \"Service is unavailable, please try again later.\"}";
        return Mono.just(new ResponseEntity<>(errorMessage, HttpStatus.SERVICE_UNAVAILABLE));
    }

    // Manejo de errores para el WebClient
    private Mono<? extends Throwable> handleWebClientError(ClientResponse clientResponse) {
        logger.error("Error from service: {}", clientResponse.statusCode());
        return Mono.error(new RuntimeException("Error from service: " + clientResponse.statusCode()));
    }

    // Metodo para preparar el cuerpo de la solicitud
    private String prepareBody(int step) {
        return String.format("{ \"data\": [{ \"header\": { \"id\": \"12345\", \"type\": \"StepsGiraffeRefrigerator\" }, \"step\": \"%d\" }] }", step);
    }

    // Extraer la respuesta del JSON
    private String extractAnswer(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            return jsonNode.get(0).get("data").get(0).get("answer").asText();
        } catch (Exception e) {
            logger.error("Error extracting answer from response: {}", e.getMessage());
            return "Error extracting answer";
        }
    }
}
