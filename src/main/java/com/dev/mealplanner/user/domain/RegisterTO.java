package com.dev.mealplanner.user.domain;

public record RegisterTO(String name, String email, String password, Double weight, Double height, Integer age, Gender gender, ActivityLevel activityLevel) {

}
