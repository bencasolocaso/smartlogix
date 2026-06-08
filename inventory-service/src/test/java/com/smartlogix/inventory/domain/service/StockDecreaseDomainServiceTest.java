package com.smartlogix.inventory.domain.service;

import com.smartlogix.inventory.domain.exception.InsufficientStockException;
import com.smartlogix.inventory.domain.model.Product;
import com.smartlogix.inventory.domain.model.Sku;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StockDecreaseDomainServiceTest {

    @Test
    void shouldDecreaseStockWhenValidationPasses() {
        // Given
        Product product = new Product(new Sku("SKU-123"), "Laptop", 10);
        StockValidationStrategy strategy = new StandardStockValidationStrategy();
        StockDecreaseDomainService service = new StockDecreaseDomainService(strategy);
        
        // When
        service.decreaseStock(product, 3);
        
        // Then
        assertThat(product.getStockQuantity()).isEqualTo(7);
    }

    @Test
    void shouldThrowExceptionWhenValidationFails() {
        // Given
        Product product = new Product(new Sku("SKU-123"), "Laptop", 2);
        StockValidationStrategy strategy = new StandardStockValidationStrategy();
        StockDecreaseDomainService service = new StockDecreaseDomainService(strategy);
        
        // When & Then
        assertThatThrownBy(() -> service.decreaseStock(product, 5))
            .isInstanceOf(InsufficientStockException.class);
    }
}
