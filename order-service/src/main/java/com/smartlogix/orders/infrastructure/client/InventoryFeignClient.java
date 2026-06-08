package com.smartlogix.orders.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@FeignClient(name = "inventory-service", url = "${INVENTORY_SERVICE_URL:http://localhost:8081}")
public interface InventoryFeignClient {
    
    @PutMapping("/api/inventory/reserve")
    void reserveInventory(@RequestBody List<InventoryReservationRequest> request);
}

record InventoryReservationRequest(String sku, int quantity) {}
