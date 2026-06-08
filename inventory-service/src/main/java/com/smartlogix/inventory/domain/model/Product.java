package com.smartlogix.inventory.domain.model;

import com.smartlogix.inventory.domain.exception.InsufficientStockException;
import lombok.Getter;

@Getter
public class Product {
    private final Sku sku;
    private String name;
    private int stockQuantity;

    public Product(Sku sku, String name, int initialStock) {
        this.sku = sku;
        this.name = name;
        this.stockQuantity = initialStock;
    }

    public void decreaseStock(int quantity) {
        if (this.stockQuantity < quantity) {
            throw new InsufficientStockException("Insufficient stock for product " + this.sku.value());
        }
        this.stockQuantity -= quantity;
    }
}
