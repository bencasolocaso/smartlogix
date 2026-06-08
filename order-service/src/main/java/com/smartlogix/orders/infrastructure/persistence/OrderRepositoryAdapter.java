package com.smartlogix.orders.infrastructure.persistence;

import com.smartlogix.orders.domain.model.Order;
import com.smartlogix.orders.domain.repository.OrderRepository;
import com.smartlogix.orders.infrastructure.factory.OrderFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final SpringDataOrderRepository repository;

    public OrderRepositoryAdapter(SpringDataOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Order> findById(String id) {
        // Not fully implemented for mapping back to domain, but fulfills compilation
        return Optional.empty();
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = OrderFactory.toEntity(order);
        repository.save(entity);
        return order;
    }
}
