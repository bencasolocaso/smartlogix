package com.smartlogix.inventory.domain.model;

import com.smartlogix.inventory.domain.exception.InsufficientStockException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductTest {

    @Test
    void shouldCreateProductWithValidData() {
        // Given
        Sku sku = new Sku("SKU-123");
        String name = "Laptop";
        int initialStock = 10;
        
        // When
        Product product = new Product(sku, name, initialStock);
        
        // Then
        assertThat(product.getSku()).isEqualTo(sku);
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getStockQuantity()).isEqualTo(initialStock);
    }

    @Test
    void shouldDecreaseStockWhenQuantityIsAvailable() {
        // Given
        Product product = new Product(new Sku("SKU-123"), "Laptop", 10);
        
        // When
        product.decreaseStock(3);
        
        // Then
        assertThat(product.getStockQuantity()).isEqualTo(7);
    }

    @Test
    void shouldThrowInsufficientStockExceptionWhenDecreasingMoreThanAvailable() {
        // Given
        Product product = new Product(new Sku("SKU-123"), "Laptop", 5);
        
        // When & Then
        assertThatThrownBy(() -> product.decreaseStock(10))
            .isInstanceOf(InsufficientStockException.class)
            .hasMessageContaining("Insufficient stock");
    }
}
