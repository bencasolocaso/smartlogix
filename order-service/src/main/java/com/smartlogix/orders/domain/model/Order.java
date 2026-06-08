package com.smartlogix.orders.domain.model;

import lombok.Getter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
public class Order {
    private final String id;
    private final String customerId;
    private OrderStatus status;
    private final List<OrderItem> items;

    public Order(String customerId, List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.items = List.copyOf(items);
        this.status = OrderStatus.PENDING;
    }

    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public OrderStatus getStatus() { return status; }
    public List<OrderItem> getItems() { return items; }

    public void confirm() {
        this.status = OrderStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = OrderStatus.CANCELLED;
    }
}
