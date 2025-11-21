package com.ssafy.minibite.dashboard.controller;

import com.ssafy.minibite.dashboard.dto.MealRecordRequest;
import com.ssafy.minibite.dashboard.dto.StatisticsResponse;
import com.ssafy.minibite.dashboard.service.DashboardService;
import com.ssafy.minibite.global.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(summary = "AI 식단 추천 등 개인 통계 조회")
    @GetMapping("/statistics")
    public ResponseEntity<ApiResponse<StatisticsResponse>> getStatistics() {
        StatisticsResponse response = dashboardService.getStatistics();
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(summary = "식단 기록 저장")
    @PostMapping("/meal-record")
    public ResponseEntity<ApiResponse<String>> createMealRecord(
            @Valid @RequestBody MealRecordRequest request
    ) {
        dashboardService.saveMealRecord(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("식단 기록 저장 완료", null));
    }
}
