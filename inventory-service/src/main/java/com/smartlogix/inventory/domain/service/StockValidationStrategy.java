package com.smartlogix.inventory.domain.service;

import com.smartlogix.inventory.domain.model.Product;

public interface StockValidationStrategy {
    void validate(Product product, int quantityToDecrease);
}
