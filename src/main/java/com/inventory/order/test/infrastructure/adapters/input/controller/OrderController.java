package com.inventory.order.test.infrastructure.adapters.input.controller;


import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import com.inventory.order.test.application.dto.CreateOrderRequest;
import com.inventory.order.test.application.dto.OrderSearchRequest;
import com.inventory.order.test.application.service.OrderQueryService;
import com.inventory.order.test.application.service.OrderService;
import com.inventory.order.test.infrastructure.entity.OrderEntity;
import com.inventory.order.test.shared.response.ApiResponse;

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
    @PreAuthorize("hasRole('USER')")
    public ApiResponse<Void> createOrder(
            @RequestBody CreateOrderRequest request) {

        orderService.createOrder(request);

        return new ApiResponse<>(
                true,
                "Order created successfully",
                null);
    }
    
    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public Page<OrderEntity> search(
            OrderSearchRequest request,
            Pageable pageable) {

        return orderQueryService.search(
                request,
                pageable);
    }
}