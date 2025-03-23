package com.dev.mealplanner.meal.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tb_item")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "meal_id")
    private Meal meal;

    private String name;

    private Double calories;

    private Double carbs;

    private Double protein;

    private Double fat;
}
