package com.smartlogix.shipping.infrastructure.persistence;

import com.smartlogix.shipping.domain.model.Shipment;
import com.smartlogix.shipping.domain.repository.ShipmentRepository;
import com.smartlogix.shipping.infrastructure.factory.ShipmentFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ShipmentRepositoryAdapter implements ShipmentRepository {

    private final SpringDataShipmentRepository jpaRepository;
    private final ShipmentFactory factory;

    @Override
    public Shipment save(Shipment shipment) {
        ShipmentEntity entity = factory.toEntity(shipment);
        ShipmentEntity saved = jpaRepository.save(entity);
        return factory.toDomain(saved);
    }

    @Override
    public Optional<Shipment> findById(String id) {
        return jpaRepository.findById(id).map(factory::toDomain);
    }

    @Override
    public Optional<Shipment> findByOrderId(String orderId) {
        return jpaRepository.findByOrderId(orderId).map(factory::toDomain);
    }
}
