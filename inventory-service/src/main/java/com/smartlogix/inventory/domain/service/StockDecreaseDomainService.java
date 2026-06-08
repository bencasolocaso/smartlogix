package com.smartlogix.inventory.domain.service;

import com.smartlogix.inventory.domain.model.Product;

public class StockDecreaseDomainService {
    
    private final StockValidationStrategy validationStrategy;

    public StockDecreaseDomainService(StockValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public void decreaseStock(Product product, int quantity) {
        validationStrategy.validate(product, quantity);
        product.decreaseStock(quantity);
    }
}
