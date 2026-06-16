package com.smartlogix.shipping.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentEntity {
    @Id
    private String id;
    private String orderId;
    
    // Origin Address
    private String originStreet;
    private String originCity;
    private String originRegion;
    private String originZipCode;

    // Destination Address
    private String destStreet;
    private String destCity;
    private String destRegion;
    private String destZipCode;

    private BigDecimal cost;
    private String status;
}
