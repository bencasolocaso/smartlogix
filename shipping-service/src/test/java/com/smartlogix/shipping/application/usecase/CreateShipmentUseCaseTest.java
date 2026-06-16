package com.smartlogix.shipping.application.usecase;

import com.smartlogix.shipping.application.dto.ShipmentDTO;
import com.smartlogix.shipping.domain.model.Address;
import com.smartlogix.shipping.domain.model.Shipment;
import com.smartlogix.shipping.domain.repository.ShipmentRepository;
import com.smartlogix.shipping.domain.service.StandardShippingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateShipmentUseCaseTest {

    private ShipmentRepository shipmentRepository;
    private CreateShipmentUseCase createShipmentUseCase;

    @BeforeEach
    void setUp() {
        shipmentRepository = mock(ShipmentRepository.class);
        createShipmentUseCase = new CreateShipmentUseCase(shipmentRepository);
    }

    @Test
    void shouldCreateShipmentAndReturnDto() {
        // Given
        String orderId = "order-123";
        Address origin = new Address("Street 1", "City A", "Region X", "000");
        Address dest = new Address("Street 2", "City B", "Region Y", "111");
        
        when(shipmentRepository.save(any(Shipment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        ShipmentDTO dto = createShipmentUseCase.execute(orderId, origin, dest, 10.0, 50.0, new StandardShippingStrategy());

        // Then
        assertThat(dto).isNotNull();
        assertThat(dto.orderId()).isEqualTo(orderId);
        // Cost: 5.00 + (10 * 0.5) + (50 * 0.1) = 5.0 + 5.0 + 5.0 = 15.00
        assertThat(dto.cost()).isEqualByComparingTo(new BigDecimal("15.00"));
        assertThat(dto.status()).isEqualTo("PENDING");
        
        verify(shipmentRepository, times(1)).save(any(Shipment.class));
    }
}
