package com.inventory.order.test.infrastructure.adapters.input.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.retry.annotation.Backoff;

import org.springframework.retry.annotation.Retryable;

import org.springframework.stereotype.Component;

import com.inventory.order.test.application.service.NotificationService;

import com.inventory.order.test.domain.event.OrderCreatedEvent;

import com.inventory.order.test.infrastructure.config.RabbitConfig;

@Component
public class OrderEventConsumer {

    private final NotificationService notificationService;

    public OrderEventConsumer(
            NotificationService notificationService) {

        this.notificationService = notificationService;
    }

    @RabbitListener(
            queues = RabbitConfig.ORDER_QUEUE)
    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    public void consume(OrderCreatedEvent event) {

        System.out.println(
                "Received order event: "
                        + event.getOrderId());

        notificationService
                .sendOrderNotification(
                        event.getOrderId());
    }
}