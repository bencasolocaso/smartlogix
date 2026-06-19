package com.smartlogix.bff.application.usecase;

import com.smartlogix.bff.application.dto.DashboardDTO;
import com.smartlogix.bff.application.dto.OrderDTO;
import com.smartlogix.bff.application.dto.ProductDTO;
import com.smartlogix.bff.infrastructure.client.InventoryClient;
import com.smartlogix.bff.infrastructure.client.OrderClient;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DashboardUseCase {

    private final InventoryClient inventoryClient;
    private final OrderClient orderClient;

    public DashboardUseCase(InventoryClient inventoryClient, OrderClient orderClient) {
        this.inventoryClient = inventoryClient;
        this.orderClient = orderClient;
    }

    public DashboardDTO getDashboardData() {
        DashboardResult inventoryResult = getInventoryDataWithFallback();
        DashboardOrderResult orderResult = getOrderDataWithFallback();

        return new DashboardDTO(
                inventoryResult.products(),
                inventoryResult.degraded(),
                orderResult.orders(),
                orderResult.degraded()
        );
    }

    private DashboardResult getInventoryDataWithFallback() {
        try {
            return new DashboardResult(inventoryClient.getAllProducts(), false);
        } catch (Exception e) {
            System.err.println("Fallback triggered for inventory: " + e.getMessage());
            return new DashboardResult(Collections.emptyList(), true);
        }
    }

    private DashboardOrderResult getOrderDataWithFallback() {
        try {
            return new DashboardOrderResult(orderClient.getRecentOrders(), false);
        } catch (Exception e) {
            System.err.println("Fallback triggered for order: " + e.getMessage());
            return new DashboardOrderResult(Collections.emptyList(), true);
        }
    }

    private record DashboardResult(List<ProductDTO> products, boolean degraded) {}
    private record DashboardOrderResult(List<OrderDTO> orders, boolean degraded) {}
}
