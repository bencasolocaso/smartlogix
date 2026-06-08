package com.smartlogix.orders.infrastructure.client;

import com.smartlogix.orders.domain.model.OrderItem;
import com.smartlogix.orders.domain.repository.InventoryClientPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InventoryServiceClientAdapter implements InventoryClientPort {

    private final InventoryFeignClient feignClient;

    public InventoryServiceClientAdapter(InventoryFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @Override
    public void reserveInventory(List<OrderItem> items) {
        List<InventoryReservationRequest> request = items.stream()
                .map(item -> new InventoryReservationRequest(item.sku(), item.quantity()))
                .collect(Collectors.toList());
        feignClient.reserveInventory(request);
    }
}
