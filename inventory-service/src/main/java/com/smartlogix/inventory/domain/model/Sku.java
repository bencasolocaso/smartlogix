package com.smartlogix.inventory.domain.model;

import com.smartlogix.inventory.domain.exception.InvalidSkuException;

public record Sku(String value) {
    public Sku {
        if (value == null || !value.matches("SKU-\\d{3,}")) {
            throw new InvalidSkuException("Invalid SKU format: " + value);
        }
    }
}
