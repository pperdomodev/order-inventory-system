package com.inventory.order.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.inventory.order.test.domain.exception.InventoryException;
import com.inventory.order.test.domain.model.Inventory;

public class InventoryTest {

    @Test
    void shouldReserveInventory() {

        Inventory inventory =
                new Inventory(
                        1L,
                        1L,
                        10,
                        0);

        inventory.reserve(5);

        assertEquals(
                5,
                inventory.getAvailableQuantity());

        assertEquals(
                5,
                inventory.getReservedQuantity());
    }

    @Test
    void shouldThrowWhenNoStock() {

        Inventory inventory =
                new Inventory(
                        1L,
                        1L,
                        2,
                        0);

        assertThrows(
                InventoryException.class,

                () -> inventory.reserve(5));
    }
}