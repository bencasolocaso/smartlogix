package com.smartlogix.shipping.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartlogix.shipping.application.dto.ShipmentDTO;
import com.smartlogix.shipping.application.usecase.CreateShipmentUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShippingController.class)
class ShippingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateShipmentUseCase createShipmentUseCase;

    @Test
    void shouldCreateShipmentAndReturn201() throws Exception {
        // Given
        CreateShipmentRequest request = new CreateShipmentRequest(
                "order-123",
                "O-Street", "O-City", "O-Reg", "123",
                "D-Street", "D-City", "D-Reg", "456",
                10.0, 100.0, "EXPRESS"
        );

        ShipmentDTO responseDto = new ShipmentDTO(
                "ship-1", "order-123",
                "O-Street", "O-City", "O-Reg", "123",
                "D-Street", "D-City", "D-Reg", "456",
                new BigDecimal("45.00"), "PENDING"
        );

        when(createShipmentUseCase.execute(eq("order-123"), any(), any(), eq(10.0), eq(100.0), any()))
                .thenReturn(responseDto);

        // When & Then
        mockMvc.perform(post("/api/shipping")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId").value("order-123"))
                .andExpect(jsonPath("$.cost").value(45.00))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }
}
