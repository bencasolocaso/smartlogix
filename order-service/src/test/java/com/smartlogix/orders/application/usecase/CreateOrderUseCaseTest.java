package com.smartlogix.orders.application.usecase;

import com.smartlogix.orders.application.dto.CreateOrderCommand;
import com.smartlogix.orders.domain.model.Order;
import com.smartlogix.orders.domain.repository.InventoryClientPort;
import com.smartlogix.orders.domain.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private InventoryClientPort inventoryClientPort;

    @InjectMocks
    private CreateOrderUseCase useCase;

    @Test
    void shouldCreateOrderAndReserveInventory() {
        // Given
        CreateOrderCommand command = new CreateOrderCommand("CUST-1", List.of(new com.smartlogix.orders.domain.model.OrderItem("SKU-123", 2)));
        Order savedOrder = new Order("CUST-1", command.items());
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        // When
        Order result = useCase.execute(command);

        // Then
        verify(inventoryClientPort).reserveInventory(command.items());
        verify(orderRepository).save(any(Order.class));
        assertThat(result).isNotNull();
        assertThat(result.getCustomerId()).isEqualTo("CUST-1");
    }
}
