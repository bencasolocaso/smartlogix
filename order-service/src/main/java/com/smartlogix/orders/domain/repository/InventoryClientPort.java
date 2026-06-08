package com.smartlogix.orders.domain.repository;

import com.smartlogix.orders.domain.model.OrderItem;
import java.util.List;

public interface InventoryClientPort {
    void reserveInventory(List<OrderItem> items);
}
