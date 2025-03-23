package com.dev.mealplanner.meal.domain;

import com.dev.mealplanner.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "tb_meal")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private List<Item> items;

    private Double totalCalories;

    private Double totalCarbs;

    private Double totalProtein;

    private Double totalFat;

}
