package com.ssafy.minibite.dashboard.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealLog {

    private Integer logId;
    private String userId;
    private LocalDateTime mealTime;
    private String photoUrl;
    private Float satisfactionScore;
    private String emotionalState;
    private String fullnessLevel;
    private Boolean nausea;
    private Boolean bloating;
    private String note;
}
