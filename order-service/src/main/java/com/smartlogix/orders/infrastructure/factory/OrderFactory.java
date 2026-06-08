package com.smartlogix.orders.infrastructure.factory;

import com.smartlogix.orders.domain.model.Order;
import com.smartlogix.orders.domain.model.OrderItem;
import com.smartlogix.orders.infrastructure.persistence.OrderEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderFactory {
    // Only toDomain needed for standard flow if we map fully, but usually both are needed.
    // For simplicity we create entity from Order.

    public static OrderEntity toEntity(Order order) {
        if (order == null) return null;
        List<com.smartlogix.orders.infrastructure.persistence.OrderItemEntity> items = order.getItems().stream()
                .map(item -> new com.smartlogix.orders.infrastructure.persistence.OrderItemEntity(item.sku(), item.quantity()))
                .collect(Collectors.toList());
        return new OrderEntity(order.getId(), order.getCustomerId(), order.getStatus(), items);
    }
}
