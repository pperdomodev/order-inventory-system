package com.inventory.order.test.infrastructure.adapters.output.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.inventory.order.test.infrastructure.entity.InventoryEntity;

import jakarta.persistence.LockModeType;

public interface InventoryJpaRepository extends JpaRepository<InventoryEntity, Long> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<InventoryEntity> findByProductId(Long productId);

}
