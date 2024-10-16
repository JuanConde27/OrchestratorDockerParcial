package com.example.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicroServiceController {

    private final Configuration configuration;

    @Value("${puerto}")
    private int puerto;

    @Autowired
    public MicroServiceController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/endpoint")
    public String retrieveLimits() {
        return "Value: " + configuration.getValue() + ", Puerto: " + puerto;
    }
}
