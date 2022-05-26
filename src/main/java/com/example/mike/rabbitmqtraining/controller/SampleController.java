package com.example.mike.rabbitmqtraining.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class SampleController {

    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    private final RabbitTemplate template;


    public SampleController(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/emit")
    public ResponseEntity<String> emit(@RequestBody Map<String, String> map) {
        logger.info("Emit to myQueue"   );
        template.setExchange("topic-exchange");
        template.convertAndSend(map.get("key"), map.get("message"));
        return ResponseEntity.ok("Success emit to queue");
    }
}
