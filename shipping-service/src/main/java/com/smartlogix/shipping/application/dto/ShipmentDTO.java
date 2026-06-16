package com.smartlogix.shipping.application.dto;

import java.math.BigDecimal;

public record ShipmentDTO(
        String id,
        String orderId,
        String originStreet,
        String originCity,
        String originRegion,
        String originZip,
        String destStreet,
        String destCity,
        String destRegion,
        String destZip,
        BigDecimal cost,
        String status
) {}
