package com.example.webhook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    @PostMapping
    public ResponseEntity<String> receiveWebhook(@RequestBody(required = false) String payload) {
        logger.info("recibido el mensaje del orquestador");
        // Opcional: Puedes procesar el payload si es necesario
        return new ResponseEntity<>("Webhook recibido correctamente", HttpStatus.OK);
    }
}