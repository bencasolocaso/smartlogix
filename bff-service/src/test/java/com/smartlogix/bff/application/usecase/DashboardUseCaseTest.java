package com.smartlogix.bff.application.usecase;

import com.smartlogix.bff.application.dto.DashboardDTO;
import com.smartlogix.bff.application.dto.ProductDTO;
import com.smartlogix.bff.infrastructure.client.InventoryClient;
import com.smartlogix.bff.infrastructure.client.OrderClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DashboardUseCaseTest {

    @Mock
    private InventoryClient inventoryClient;

    @Mock
    private OrderClient orderClient;

    @InjectMocks
    private DashboardUseCase useCase;

    @Test
    void shouldReturnDashboardDataWhenClientsSucceed() {
        // Given
        when(inventoryClient.getAllProducts()).thenReturn(List.of(new ProductDTO("SKU", "Name", 10)));
        when(orderClient.getRecentOrders()).thenReturn(List.of());

        // When
        DashboardDTO dto = useCase.getDashboardData();

        // Then
        assertThat(dto.products()).hasSize(1);
        assertThat(dto.inventoryServiceDegraded()).isFalse();
        assertThat(dto.recentOrders()).isEmpty();
        assertThat(dto.orderServiceDegraded()).isFalse();
    }
}
