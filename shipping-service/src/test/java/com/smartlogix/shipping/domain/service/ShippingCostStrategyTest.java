package com.smartlogix.shipping.domain.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ShippingCostStrategyTest {

    @Test
    void shouldCalculateStandardShippingCost() {
        // Given
        ShippingCostStrategy strategy = new StandardShippingStrategy();
        double weightInKg = 5.0;
        double distanceInKm = 100.0;

        // When
        BigDecimal cost = strategy.calculateCost(weightInKg, distanceInKm);

        // Then
        // Standard cost = 5.00 base + (weight * 0.5) + (distance * 0.1)
        // 5.00 + 2.5 + 10.0 = 17.50
        assertThat(cost).isEqualByComparingTo(new BigDecimal("17.50"));
    }

    @Test
    void shouldCalculateExpressShippingCost() {
        // Given
        ShippingCostStrategy strategy = new ExpressShippingStrategy();
        double weightInKg = 5.0;
        double distanceInKm = 100.0;

        // When
        BigDecimal cost = strategy.calculateCost(weightInKg, distanceInKm);

        // Then
        // Express cost = 15.00 base + (weight * 1.0) + (distance * 0.2)
        // 15.00 + 5.0 + 20.0 = 40.00
        assertThat(cost).isEqualByComparingTo(new BigDecimal("40.00"));
    }
}
