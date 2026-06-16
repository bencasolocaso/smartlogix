package com.smartlogix.shipping.presentation.controller;

import com.smartlogix.shipping.application.dto.ShipmentDTO;
import com.smartlogix.shipping.application.usecase.CreateShipmentUseCase;
import com.smartlogix.shipping.domain.model.Address;
import com.smartlogix.shipping.domain.service.ExpressShippingStrategy;
import com.smartlogix.shipping.domain.service.ShippingCostStrategy;
import com.smartlogix.shipping.domain.service.StandardShippingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final CreateShipmentUseCase createShipmentUseCase;

    @PostMapping
    public ResponseEntity<ShipmentDTO> createShipment(@RequestBody CreateShipmentRequest request) {
        
        Address origin = new Address(request.originStreet(), request.originCity(), request.originRegion(), request.originZipCode());
        Address dest = new Address(request.destStreet(), request.destCity(), request.destRegion(), request.destZipCode());
        
        ShippingCostStrategy strategy = "EXPRESS".equalsIgnoreCase(request.strategyType()) 
            ? new ExpressShippingStrategy() 
            : new StandardShippingStrategy();

        ShipmentDTO result = createShipmentUseCase.execute(
                request.orderId(),
                origin,
                dest,
                request.weightInKg(),
                request.distanceInKm(),
                strategy
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
