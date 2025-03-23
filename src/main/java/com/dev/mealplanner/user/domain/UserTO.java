package com.dev.mealplanner.user.domain;

public record UserTO(String name, String email, Gender gender, ActivityLevel activityLevel, Integer age, Double weight, Double Height, Double metabolicRate) {
}
