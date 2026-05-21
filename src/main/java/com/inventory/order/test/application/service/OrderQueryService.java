package com.inventory.order.test.application.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inventory.order.test.application.dto.OrderSearchRequest;
import com.inventory.order.test.infrastructure.adapters.output.persistence.OrderJpaRepository;
import com.inventory.order.test.infrastructure.adapters.output.persistence.specification.OrderSpecification;
import com.inventory.order.test.infrastructure.entity.OrderEntity;

@Service
public class OrderQueryService {

    private final OrderJpaRepository repository;

    public OrderQueryService(
            OrderJpaRepository repository) {

        this.repository = repository;
    }

    @Cacheable("orders-search")
    public Page<OrderEntity> search(
            OrderSearchRequest request,
            Pageable pageable) {

        System.out.println(
                "Fetching orders from database...");

        return repository.findAll(
                OrderSpecification.search(request),
                pageable);
    }
}