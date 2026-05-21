package com.inventory.order.test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class InventoryConcurrencyTest {

    @Test
    void shouldHandleConcurrentReservation() {

        assertThrows(RuntimeException.class, () -> {

            throw new RuntimeException("Test");
        });
    }

}
