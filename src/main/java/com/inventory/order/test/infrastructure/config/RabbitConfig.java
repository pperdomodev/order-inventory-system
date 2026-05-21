package com.inventory.order.test.infrastructure.config;

import org.springframework.amqp.core.Binding;

import org.springframework.amqp.core.BindingBuilder;

import org.springframework.amqp.core.DirectExchange;

import org.springframework.amqp.core.Queue;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String ORDER_QUEUE =
            "order.events";

    public static final String ORDER_DLQ =
            "order.events.dlq";

    public static final String EXCHANGE =
            "order.exchange";

    public static final String ROUTING_KEY =
            "order.routing.key";

    @Bean
    public Queue orderQueue() {

        return new Queue(ORDER_QUEUE, true);
    }

    @Bean
    public Queue deadLetterQueue() {

        return new Queue(ORDER_DLQ, true);
    }

    @Bean
    public DirectExchange exchange() {

        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding binding() {

        return BindingBuilder
                .bind(orderQueue())
                .to(exchange())
                .with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter
    jsonMessageConverter() {

        return new Jackson2JsonMessageConverter();
    }
}