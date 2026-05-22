package com.inventory.order.test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.inventory.order.test.application.dto.CreateOrderRequest;
import com.inventory.order.test.application.dto.OrderItemRequest;
import com.inventory.order.test.application.service.OrderService;
import com.inventory.order.test.domain.model.Inventory;
import com.inventory.order.test.domain.ports.InventoryRepositoryPort;
import com.inventory.order.test.domain.ports.OrderRepositoryPort;
import com.inventory.order.test.infrastructure.adapters.output.messaging.EventPublisher;

public class OrderServiceTest {

    @Mock
    private InventoryRepositoryPort inventoryRepositoryPort;

    @Mock
    private OrderRepositoryPort orderRepositoryPort;

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setup() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateOrder() {

        Inventory inventory =
                new Inventory(
                        1L,
                        1L,
                        10,
                        0);

        when(
                inventoryRepositoryPort
                        .findByProductId(1L))

                .thenReturn(
                        Optional.of(inventory));

        CreateOrderRequest request =
                new CreateOrderRequest();

        request.setUserId(1L);

        OrderItemRequest item =
                new OrderItemRequest();

        item.setProductId(1L);

        item.setQuantity(2);

        item.setPrice(
                BigDecimal.valueOf(100));

        request.setItems(List.of(item));

        orderService.createOrder(request);

        verify(inventoryRepositoryPort)
                .save(org.mockito.ArgumentMatchers.any());

        verify(eventPublisher)
                .publish(org.mockito.ArgumentMatchers.any());
    }
}