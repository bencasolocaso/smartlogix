package com.smartlogix.shipping.domain.service;

import java.math.BigDecimal;

public interface ShippingCostStrategy {
    BigDecimal calculateCost(double weightInKg, double distanceInKm);
}
