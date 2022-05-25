package com.example.mike.rabbitmqtraining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class RabbitListenner {

    private static final Logger logger = LoggerFactory.getLogger(RabbitListenner.class);

    @RabbitListener(queues = "myQueue")
    public void processMyQueue(String message) {
        logger.info("Received first from myQueue: {}", message);
    }

    @RabbitListener(queues = "myQueue")
    public void processMyQueue2(String message) {
        logger.info("Recieved second from myQueue: {}", message);
    }
}
