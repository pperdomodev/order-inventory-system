package com.inventory.order.test.infrastructure.adapters.input.controller;

import org.springframework.web.bind.annotation.*;

import com.inventory.order.test.application.dto.CreateOrderRequest;
import com.inventory.order.test.application.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String createOrder(
            @RequestBody CreateOrderRequest request) {

        orderService.createOrder(request);

        return "Order created";
    }
}