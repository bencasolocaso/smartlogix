package com.smartlogix.shipping.infrastructure.factory;

import com.smartlogix.shipping.domain.model.Address;
import com.smartlogix.shipping.domain.model.Shipment;
import com.smartlogix.shipping.domain.model.ShipmentStatus;
import com.smartlogix.shipping.infrastructure.persistence.ShipmentEntity;
import org.springframework.stereotype.Component;

@Component
public class ShipmentFactory {

    public ShipmentEntity toEntity(Shipment domain) {
        return new ShipmentEntity(
                domain.getId(),
                domain.getOrderId(),
                domain.getOrigin().street(),
                domain.getOrigin().city(),
                domain.getOrigin().region(),
                domain.getOrigin().zipCode(),
                domain.getDestination().street(),
                domain.getDestination().city(),
                domain.getDestination().region(),
                domain.getDestination().zipCode(),
                domain.getCost(),
                domain.getStatus().name()
        );
    }

    public Shipment toDomain(ShipmentEntity entity) {
        Address origin = new Address(entity.getOriginStreet(), entity.getOriginCity(), entity.getOriginRegion(), entity.getOriginZipCode());
        Address dest = new Address(entity.getDestStreet(), entity.getDestCity(), entity.getDestRegion(), entity.getDestZipCode());
        
        return new Shipment(
                entity.getId(),
                entity.getOrderId(),
                origin,
                dest,
                entity.getCost(),
                ShipmentStatus.valueOf(entity.getStatus())
        );
    }
}
