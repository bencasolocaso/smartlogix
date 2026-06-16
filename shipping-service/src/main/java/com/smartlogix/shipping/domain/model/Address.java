package com.smartlogix.shipping.domain.model;

public record Address(String street, String city, String region, String zipCode) {
    public Address {
        if (street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be empty");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be empty");
        }
    }
}
