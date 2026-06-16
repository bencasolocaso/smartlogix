package com.smartlogix.shipping.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataShipmentRepository extends JpaRepository<ShipmentEntity, String> {
    Optional<ShipmentEntity> findByOrderId(String orderId);
}
