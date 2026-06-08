package com.smartlogix.bff.presentation.controller;

import com.smartlogix.bff.application.dto.DashboardDTO;
import com.smartlogix.bff.application.usecase.DashboardUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bff")
public class DashboardController {

    private final DashboardUseCase dashboardUseCase;

    public DashboardController(DashboardUseCase dashboardUseCase) {
        this.dashboardUseCase = dashboardUseCase;
    }

    @GetMapping("/dashboard")
    public DashboardDTO getDashboard() {
        return dashboardUseCase.getDashboardData();
    }
}
