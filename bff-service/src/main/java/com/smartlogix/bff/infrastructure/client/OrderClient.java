package com.smartlogix.bff.infrastructure.client;

import com.smartlogix.bff.application.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "order-service", url = "${ORDER_SERVICE_URL:http://localhost:8082}")
public interface OrderClient {

    @GetMapping("/api/orders/recent")
    List<OrderDTO> getRecentOrders();
}
