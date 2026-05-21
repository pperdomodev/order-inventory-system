package com.inventory.order.test.application.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Async
    public void sendOrderNotification(Long orderId) {

        System.out.println(
                "Sending notification for order: "
                        + orderId);

        try {

            Thread.sleep(3000);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }

        System.out.println(
                "Notification sent successfully");
    }
}