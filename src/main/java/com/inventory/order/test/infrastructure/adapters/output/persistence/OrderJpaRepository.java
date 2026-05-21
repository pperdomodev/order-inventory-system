package com.inventory.order.test.infrastructure.adapters.output.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.inventory.order.test.infrastructure.entity.OrderEntity;

public interface OrderJpaRepository
        extends JpaRepository<OrderEntity, Long>,
                JpaSpecificationExecutor<OrderEntity> {
	
	Optional<OrderEntity> findById(Long id);
	
	
}