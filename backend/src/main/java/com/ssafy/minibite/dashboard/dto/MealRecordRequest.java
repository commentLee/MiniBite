package com.ssafy.minibite.dashboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealRecordRequest {

    @Schema(description = "기록할 사용자 ID(임시; 인증 연동 시 대체 예정)")
    private String userId;

    @NotNull
    @Schema(description = "식사 시간", example = "2025-11-21T12:00:00")
    private LocalDateTime mealTime;

    @Min(1)
    @Max(5)
    @Schema(description = "만족도(1~5)")
    private Integer satisfaction;

    @Schema(description = "부작용 목록", example = "[\"nausea\",\"bloating\"]")
    private List<String> sideEffects;

    @Schema(description = "메모/비고")
    private String note;
}
