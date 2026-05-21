package com.inventory.order.test.infrastructure.adapters.input.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

import com.inventory.order.test.infrastructure.config.RabbitConfig;

@Component
public class DeadLetterConsumer {

    @RabbitListener(
            queues = RabbitConfig.ORDER_DLQ)
    public void processDeadLetter(Object message) {

        System.out.println(
                "DLQ MESSAGE RECEIVED: "
                        + message);
    }
}