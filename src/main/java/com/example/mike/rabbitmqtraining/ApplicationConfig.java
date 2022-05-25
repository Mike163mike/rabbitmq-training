package com.example.mike.rabbitmqtraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration
@ComponentScan
@Import(RabbitConfig.class)
public class ApplicationConfig {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }
}
