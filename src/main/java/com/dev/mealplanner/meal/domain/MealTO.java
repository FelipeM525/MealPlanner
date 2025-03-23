package com.dev.mealplanner.meal.domain;

import lombok.Builder;

import java.util.List;
@Builder
public record MealTO(Long id, List<ItemTO> items, Double totalCalories, Double totalCarbs, Double totalProtein, Double totalFat) {
}
