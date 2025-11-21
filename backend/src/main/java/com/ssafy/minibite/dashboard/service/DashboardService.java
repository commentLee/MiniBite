package com.ssafy.minibite.dashboard.service;

import com.ssafy.minibite.dashboard.domain.MealLog;
import com.ssafy.minibite.dashboard.dto.MealRecordRequest;
import com.ssafy.minibite.dashboard.dto.StatisticsResponse;
import com.ssafy.minibite.dashboard.repository.MealLogMapper;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final MealLogMapper mealLogMapper;

    @Transactional
    public void saveMealRecord(MealRecordRequest request) {
        Integer id = mealLogMapper.nextId();
        MealLog mealLog = MealLog.builder()
                .logId(id)
                .userId(request.getUserId() != null ? request.getUserId() : "demo-user")
                .mealTime(request.getMealTime())
                .photoUrl(null)
                .satisfactionScore(request.getSatisfaction() != null ? request.getSatisfaction().floatValue() : null)
                .emotionalState(null)
                .fullnessLevel(null)
                .nausea(request.getSideEffects() != null && request.getSideEffects().contains("nausea"))
                .bloating(request.getSideEffects() != null && request.getSideEffects().contains("bloating"))
                .note(request.getNote())
                .build();
        mealLogMapper.insertMealLog(mealLog);
    }

    @Transactional(readOnly = true)
    public StatisticsResponse getStatistics() {
        // TODO: DB 기반 통계로 교체. 현재는 더미 데이터 반환.
        return StatisticsResponse.builder()
                .weightTrend(List.of(70.0f, 69.5f, 69.2f, 68.9f))
                .favoriteFoods(List.of("두부", "연어", "고구마"))
                .averageSatisfaction(4.2f)
                .safeFoodList(List.of("고구마", "닭가슴살", "현미밥"))
                .build();
    }
}
