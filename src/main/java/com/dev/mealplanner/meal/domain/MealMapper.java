package com.dev.mealplanner.meal.domain;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MealMapper {
    Meal destinationToSource(MealTO to);
    MealTO sourceToDestination(Meal meal);

}
