package com.smartlogix.shipping.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Shipment {
    private final String id;
    private final String orderId;
    private final Address origin;
    private final Address destination;
    private final BigDecimal cost;
    private ShipmentStatus status;

    public Shipment(String orderId, Address origin, Address destination, BigDecimal cost) {
        this.id = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.origin = origin;
        this.destination = destination;
        this.cost = cost;
        this.status = ShipmentStatus.PENDING;
    }

    // Required by Infrastructure Factory
    public Shipment(String id, String orderId, Address origin, Address destination, BigDecimal cost, ShipmentStatus status) {
        this.id = id;
        this.orderId = orderId;
        this.origin = origin;
        this.destination = destination;
        this.cost = cost;
        this.status = status;
    }

    public void updateStatus(ShipmentStatus newStatus) {
        this.status = newStatus;
    }

    public String getId() { return id; }
    public String getOrderId() { return orderId; }
    public Address getOrigin() { return origin; }
    public Address getDestination() { return destination; }
    public BigDecimal getCost() { return cost; }
    public ShipmentStatus getStatus() { return status; }
}
