package com.ssafy.minibite.dashboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class StatisticsResponse {

    @Schema(description = "체중 변화 추이")
    private List<Float> weightTrend;

    @Schema(description = "선호 음식")
    private List<String> favoriteFoods;

    @Schema(description = "평균 만족도")
    private Float averageSatisfaction;

    @Schema(description = "세이프 푸드 목록")
    private List<String> safeFoodList;
}
