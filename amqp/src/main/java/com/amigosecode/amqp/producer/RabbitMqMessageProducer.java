package com.amigosecode.amqp.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class RabbitMqMessageProducer {

    private AmqpTemplate amqpTemplate;

    public void publish(String exchange, String routingKey, Object payload){
        log.info("Publishing to :{}, routingKey:{}, with payload:{}", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to :{}, routingKey:{}, with payload:{}", exchange, routingKey, payload);
    }
}
