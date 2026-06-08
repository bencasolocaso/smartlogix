package com.smartlogix.inventory.domain.model;

import com.smartlogix.inventory.domain.exception.InvalidSkuException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SkuTest {

    @Test
    void shouldCreateSkuWhenFormatIsValid() {
        // Given
        String validFormat = "SKU-123";
        
        // When
        Sku sku = new Sku(validFormat);
        
        // Then
        assertThat(sku.value()).isEqualTo(validFormat);
    }

    @Test
    void shouldThrowInvalidSkuExceptionWhenFormatIsInvalid() {
        // Given
        String invalidFormat = "123-SKU";
        
        // When & Then
        assertThatThrownBy(() -> new Sku(invalidFormat))
            .isInstanceOf(InvalidSkuException.class)
            .hasMessageContaining("Invalid SKU format");
    }

    @Test
    void shouldThrowInvalidSkuExceptionWhenValueIsNull() {
        // When & Then
        assertThatThrownBy(() -> new Sku(null))
            .isInstanceOf(InvalidSkuException.class)
            .hasMessageContaining("Invalid SKU format");
    }
}
