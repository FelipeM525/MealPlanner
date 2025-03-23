package com.dev.mealplanner.meal.repository;

import com.dev.mealplanner.meal.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
