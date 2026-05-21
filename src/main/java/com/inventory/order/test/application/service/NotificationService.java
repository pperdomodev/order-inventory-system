package com.inventory.order.test.application.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	@Async
    public void sendOrderNotification(Long orderId) {

        System.out.println("Sending notification for order: " + orderId);
    }

}
