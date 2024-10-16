package com.example.batch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BatchConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}