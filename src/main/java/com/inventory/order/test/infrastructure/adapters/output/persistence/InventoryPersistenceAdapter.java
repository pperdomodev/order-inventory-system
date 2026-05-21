package com.inventory.order.test.infrastructure.adapters.output.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.inventory.order.test.domain.model.Inventory;
import com.inventory.order.test.domain.ports.InventoryRepositoryPort;
import com.inventory.order.test.infrastructure.entity.InventoryEntity;

@Component
public class InventoryPersistenceAdapter
        implements InventoryRepositoryPort {

    private final InventoryJpaRepository repository;

    public InventoryPersistenceAdapter(
            InventoryJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public Optional<Inventory> findByProductId(Long productId) {

        return repository.findByProductId(productId)
                .map(entity -> new Inventory(
                        entity.getId(),
                        entity.getProductId(),
                        entity.getAvailableQuantity(),
                        entity.getReservedQuantity()));
    }

    @Override
    public Inventory save(Inventory inventory) {

        InventoryEntity entity =
                repository.findById(inventory.getId())
                        .orElse(new InventoryEntity());

        entity.setProductId(inventory.getProductId());

        entity.setAvailableQuantity(
                inventory.getAvailableQuantity());

        entity.setReservedQuantity(
                inventory.getReservedQuantity());

        InventoryEntity saved = repository.save(entity);

        return new Inventory(
                saved.getId(),
                saved.getProductId(),
                saved.getAvailableQuantity(),
                saved.getReservedQuantity());
    }
}