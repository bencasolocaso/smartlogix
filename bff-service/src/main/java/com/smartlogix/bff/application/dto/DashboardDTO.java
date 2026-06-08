package com.smartlogix.bff.application.dto;

import java.util.List;

public record DashboardDTO(
    List<ProductDTO> products,
    boolean inventoryServiceDegraded,
    List<OrderDTO> recentOrders,
    boolean orderServiceDegraded
) {}
