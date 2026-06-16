package com.smartlogix.shipping.presentation.controller;

public record CreateShipmentRequest(
        String orderId,
        String originStreet,
        String originCity,
        String originRegion,
        String originZipCode,
        String destStreet,
        String destCity,
        String destRegion,
        String destZipCode,
        double weightInKg,
        double distanceInKm,
        String strategyType // "STANDARD" or "EXPRESS"
) {}
