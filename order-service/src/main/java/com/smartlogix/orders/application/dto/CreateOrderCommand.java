package com.smartlogix.orders.application.dto;

import com.smartlogix.orders.domain.model.OrderItem;
import java.util.List;

public record CreateOrderCommand(String customerId, List<OrderItem> items) {}
