package com.smartlogix.bff.infrastructure.client;

import com.smartlogix.bff.application.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "inventory-service", url = "${INVENTORY_SERVICE_URL:http://localhost:8081}")
public interface InventoryClient {

    @GetMapping("/api/inventory/products")
    List<ProductDTO> getAllProducts();
}
