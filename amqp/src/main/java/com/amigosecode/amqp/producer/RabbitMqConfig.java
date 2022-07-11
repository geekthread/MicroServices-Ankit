package com.amigosecode.amqp.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@AllArgsConstructor
public class RabbitMqConfig {
    private ConnectionFactory connectionFactory;

    @Bean
    public AmqpTemplate amqpTemplate(){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jackSonMessageConverter());
        return  template;
    }
    @Bean
    public MessageConverter jackSonMessageConverter(){
        MessageConverter jacksonMessageConverter = new Jackson2JsonMessageConverter();
        return  jacksonMessageConverter;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(){
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jackSonMessageConverter());
        return factory;
    }

}
