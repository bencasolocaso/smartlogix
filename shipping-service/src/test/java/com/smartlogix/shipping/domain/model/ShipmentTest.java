package com.smartlogix.shipping.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ShipmentTest {

    @Test
    void shouldCreateShipment() {
        // Given
        String orderId = UUID.randomUUID().toString();
        Address origin = new Address("Av. Principal 123", "Santiago", "RM", "12345");
        Address destination = new Address("Calle Sur 456", "Concepción", "Biobío", "67890");
        BigDecimal cost = new BigDecimal("17.50");

        // When
        Shipment shipment = new Shipment(orderId, origin, destination, cost);

        // Then
        assertThat(shipment.getId()).isNotNull();
        assertThat(shipment.getOrderId()).isEqualTo(orderId);
        assertThat(shipment.getStatus()).isEqualTo(ShipmentStatus.PENDING);
        assertThat(shipment.getCost()).isEqualTo(cost);
    }
}
