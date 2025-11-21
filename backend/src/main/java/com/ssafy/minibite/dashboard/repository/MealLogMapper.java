package com.ssafy.minibite.dashboard.repository;

import com.ssafy.minibite.dashboard.domain.MealLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MealLogMapper {

    Integer nextId();

    void insertMealLog(MealLog mealLog);
}
