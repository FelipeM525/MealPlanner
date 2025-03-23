package com.dev.mealplanner.meal.repository;

import com.dev.mealplanner.meal.domain.Meal;
import com.dev.mealplanner.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {

    Optional<List<Meal>> findAllByUser(User user);


}
