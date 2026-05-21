package com.inventory.order.test.infrastructure.adapters.input.controller;


import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import com.inventory.order.test.application.dto.CreateOrderRequest;
import com.inventory.order.test.application.dto.OrderSearchRequest;
import com.inventory.order.test.application.service.OrderQueryService;
import com.inventory.order.test.application.service.OrderService;
import com.inventory.order.test.infrastructure.entity.OrderEntity;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderQueryService orderQueryService;

    public OrderController(
            OrderService orderService,
            OrderQueryService orderQueryService) {

        this.orderService = orderService;
        this.orderQueryService = orderQueryService;
    }
    @PostMapping
    public String createOrder(
            @RequestBody CreateOrderRequest request) {

        orderService.createOrder(request);

        return "Order created";
    }
    
    @GetMapping("/search")
    public Page<OrderEntity> search(
            OrderSearchRequest request,
            Pageable pageable) {

        return orderQueryService.search(
                request,
                pageable);
    }
}