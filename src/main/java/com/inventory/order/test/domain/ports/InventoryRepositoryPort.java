package com.inventory.order.test.domain.ports;

import java.util.Optional;

import com.inventory.order.test.domain.model.Inventory;

public interface InventoryRepositoryPort {

    Optional<Inventory> findByProductId(Long productId);

    Inventory save(Inventory inventory);
}