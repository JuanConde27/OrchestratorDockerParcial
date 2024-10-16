package co.com.vanegas.microservice.resolveEnigmaApi.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("get-one-enigma-step-api")
                .packagesToScan("co.com.vanegas.microservice.resolveEnigmaApi.api")
                .build();
    }
}