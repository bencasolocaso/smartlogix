package com.smartlogix.orders.domain.repository;

import com.smartlogix.orders.domain.model.Order;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(String id);
    Order save(Order order);
}
