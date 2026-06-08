package com.smartlogix.bff.application.dto;

import java.util.List;

public record OrderDTO(String id, String customerId, String status, List<OrderItemDTO> items) {}

record OrderItemDTO(String sku, int quantity) {}
