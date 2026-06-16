package com.smartlogix.shipping.domain.service;

import java.math.BigDecimal;

public class StandardShippingStrategy implements ShippingCostStrategy {
    
    private static final BigDecimal BASE_COST = new BigDecimal("5.00");
    private static final BigDecimal WEIGHT_FACTOR = new BigDecimal("0.5");
    private static final BigDecimal DISTANCE_FACTOR = new BigDecimal("0.1");

    @Override
    public BigDecimal calculateCost(double weightInKg, double distanceInKm) {
        BigDecimal weightCost = WEIGHT_FACTOR.multiply(BigDecimal.valueOf(weightInKg));
        BigDecimal distanceCost = DISTANCE_FACTOR.multiply(BigDecimal.valueOf(distanceInKm));
        return BASE_COST.add(weightCost).add(distanceCost);
    }
}
