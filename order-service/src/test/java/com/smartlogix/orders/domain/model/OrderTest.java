package com.smartlogix.orders.domain.model;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    @Test
    void shouldCreateOrderWithItems() {
        // Given
        String customerId = "CUST-001";
        OrderItem item1 = new OrderItem("SKU-123", 2);
        OrderItem item2 = new OrderItem("SKU-456", 1);
        
        // When
        Order order = new Order(customerId, List.of(item1, item2));
        
        // Then
        assertThat(order.getCustomerId()).isEqualTo(customerId);
        assertThat(order.getItems()).hasSize(2);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.PENDING);
    }

    @Test
    void shouldThrowExceptionWhenOrderHasNoItems() {
        // Given
        String customerId = "CUST-001";
        
        // When & Then
        assertThatThrownBy(() -> new Order(customerId, List.of()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Order must have at least one item");
    }
}
