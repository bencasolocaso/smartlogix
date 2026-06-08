package com.smartlogix.orders.application.usecase;

import com.smartlogix.orders.application.dto.CreateOrderCommand;
import com.smartlogix.orders.domain.model.Order;
import com.smartlogix.orders.domain.repository.InventoryClientPort;
import com.smartlogix.orders.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final InventoryClientPort inventoryClientPort;

    public CreateOrderUseCase(OrderRepository orderRepository, InventoryClientPort inventoryClientPort) {
        this.orderRepository = orderRepository;
        this.inventoryClientPort = inventoryClientPort;
    }

    public Order execute(CreateOrderCommand command) {
        // 1. Reserve Inventory
        inventoryClientPort.reserveInventory(command.items());
        
        // 2. Create Order
        Order order = new Order(command.customerId(), command.items());
        
        // 3. Save Order
        return orderRepository.save(order);
    }
}
