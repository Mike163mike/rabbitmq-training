package com.example.mike.rabbitmqtraining.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    private final AmqpTemplate template;


    public SampleController(AmqpTemplate template) {
        this.template = template;
    }

        @PostMapping("/emit")
    public ResponseEntity<String> emit(@RequestBody String message) {
        logger.info("Emit to myQueue", message);
        template.convertAndSend("myQueue", message);
        return ResponseEntity.ok("Success emit to queue");
        }
}
