package com.example.mike.rabbitmqtraining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private static final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue myQueue1() {
        return new Queue("myQueue1");
    }

    @Bean
    public Queue myQueue2() {
        return new Queue("myQueue2");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

//    @Bean
//    public DirectExchange directExchange() {
//        return new DirectExchange("direct-exchange");
//    }

//    @Bean
//    public Binding binding1() {
//        return BindingBuilder.bind(myQueue1()).to(fanoutExchange());
//    }
//
//    @Bean
//    public Binding binding2() {
//        return BindingBuilder.bind(myQueue2()).to(fanoutExchange());
//    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(myQueue1()).to(topicExchange()).with("one.*");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(myQueue2()).to(topicExchange()).with("*.second");
    }

//    @Bean
//    public Binding binding3() {
//        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("info");
//    }

//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setQueueNames();
//        container.setMessageListener(message -> logger.info("Received from myQueue: " + new String(message.getBody())));
//        return container;
//    }
}
