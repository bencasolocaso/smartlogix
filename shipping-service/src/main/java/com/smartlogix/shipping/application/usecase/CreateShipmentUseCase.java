package com.smartlogix.shipping.application.usecase;

import com.smartlogix.shipping.application.dto.ShipmentDTO;
import com.smartlogix.shipping.domain.model.Address;
import com.smartlogix.shipping.domain.model.Shipment;
import com.smartlogix.shipping.domain.repository.ShipmentRepository;
import com.smartlogix.shipping.domain.service.ShippingCostStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateShipmentUseCase {

    private final ShipmentRepository shipmentRepository;

    @Transactional
    public ShipmentDTO execute(String orderId, Address origin, Address destination, double weightInKg, double distanceInKm, ShippingCostStrategy costStrategy) {
        BigDecimal cost = costStrategy.calculateCost(weightInKg, distanceInKm);
        
        Shipment shipment = new Shipment(orderId, origin, destination, cost);
        Shipment savedShipment = shipmentRepository.save(shipment);

        return new ShipmentDTO(
                savedShipment.getId(),
                savedShipment.getOrderId(),
                savedShipment.getOrigin().street(),
                savedShipment.getOrigin().city(),
                savedShipment.getOrigin().region(),
                savedShipment.getOrigin().zipCode(),
                savedShipment.getDestination().street(),
                savedShipment.getDestination().city(),
                savedShipment.getDestination().region(),
                savedShipment.getDestination().zipCode(),
                savedShipment.getCost(),
                savedShipment.getStatus().name()
        );
    }
}
