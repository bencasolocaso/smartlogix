package com.smartlogix.bff.application.usecase;

import com.smartlogix.bff.application.dto.DashboardDTO;
import com.smartlogix.bff.application.dto.OrderDTO;
import com.smartlogix.bff.application.dto.ProductDTO;
import com.smartlogix.bff.infrastructure.client.InventoryClient;
import com.smartlogix.bff.infrastructure.client.OrderClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DashboardUseCase {

    private final InventoryClient inventoryClient;
    private final OrderClient orderClient;

    public DashboardUseCase(InventoryClient inventoryClient, OrderClient orderClient) {
        this.inventoryClient = inventoryClient;
        this.orderClient = orderClient;
    }

    public DashboardDTO getDashboardData() {
        DashboardResult inventoryResult = getInventoryData();
        DashboardOrderResult orderResult = getOrderData();

        return new DashboardDTO(
                inventoryResult.products(),
                inventoryResult.degraded(),
                orderResult.orders(),
                orderResult.degraded()
        );
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "inventoryFallback")
    @Retry(name = "inventoryService")
    public DashboardResult getInventoryData() {
        return new DashboardResult(inventoryClient.getAllProducts(), false);
    }

    public DashboardResult inventoryFallback(Exception e) {
        return new DashboardResult(Collections.emptyList(), true);
    }

    @CircuitBreaker(name = "orderService", fallbackMethod = "orderFallback")
    @Retry(name = "orderService")
    public DashboardOrderResult getOrderData() {
        return new DashboardOrderResult(orderClient.getRecentOrders(), false);
    }

    public DashboardOrderResult orderFallback(Exception e) {
        return new DashboardOrderResult(Collections.emptyList(), true);
    }

    private record DashboardResult(List<ProductDTO> products, boolean degraded) {}
    private record DashboardOrderResult(List<OrderDTO> orders, boolean degraded) {}
}
