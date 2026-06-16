package com.smartlogix.shipping.domain.repository;

import com.smartlogix.shipping.domain.model.Shipment;

import java.util.Optional;

public interface ShipmentRepository {
    Shipment save(Shipment shipment);
    Optional<Shipment> findById(String id);
    Optional<Shipment> findByOrderId(String orderId);
}
