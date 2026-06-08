package com.smartlogix.orders.domain.model;

public record OrderItem(String sku, int quantity) {
    public OrderItem {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }
}
