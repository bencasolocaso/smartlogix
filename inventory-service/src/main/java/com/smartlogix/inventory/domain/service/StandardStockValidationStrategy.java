package com.smartlogix.inventory.domain.service;

import com.smartlogix.inventory.domain.model.Product;
import com.smartlogix.inventory.domain.exception.InsufficientStockException;

public class StandardStockValidationStrategy implements StockValidationStrategy {
    @Override
    public void validate(Product product, int quantityToDecrease) {
        if (product.getStockQuantity() < quantityToDecrease) {
            throw new InsufficientStockException("Standard validation failed: Insufficient stock for " + product.getSku().value());
        }
    }
}
