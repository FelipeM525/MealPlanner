package com.dev.mealplanner.meal.service;

import com.dev.mealplanner.meal.domain.Meal;
import com.dev.mealplanner.meal.domain.MealMapper;
import com.dev.mealplanner.meal.domain.MealTO;
import com.dev.mealplanner.meal.repository.ItemRepository;
import com.dev.mealplanner.meal.repository.MealRepository;
import com.dev.mealplanner.user.domain.User;
import com.dev.mealplanner.user.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MealService {

    private UserRepository userRepository;

    private MealRepository mealRepository;

    private ItemRepository itemRepository;

    private MealMapper mealMapper;

    public List<MealTO> getMealsByUser() {
        User currentUser = getCurrentUser();

        List<Meal> meals = mealRepository.findAllByUser(currentUser).orElseThrow(() -> new RuntimeException(String.format("No meals found for user: %s", currentUser.getName())));

        List<MealTO> response = meals.stream().map(meal -> mealMapper.sourceToDestination(meal)).toList();

        return response;
    }

    public Map<String,String> addMeal(MealTO to) {
        Meal meal = mealMapper.destinationToSource(to);
        meal.setUser(getCurrentUser());
        mealRepository.save(meal);
        return Map.of("Status", "Meal Added successfully!");
    }

    public Map<String,String> deleteMeal(Long mealId) {
        mealRepository.findById(mealId).orElseThrow(() -> new RuntimeException("Meal Not Found!"));

        mealRepository.deleteById(mealId);

        return Map.of("status","Meal deleted successfully!");
    }

    public Map<String, String> deleteItem(Long mealId, Long itemId) {
        Meal meal = mealRepository.findById(mealId).orElseThrow(() -> new RuntimeException("Meal not found!"));
        meal.getItems().stream().filter(item -> Objects.equals(item.getId(), itemId)).findFirst().orElseThrow(() -> new RuntimeException("item does not belong to specified meal!"));
        itemRepository.deleteById(itemId);

        return Map.of("status", "Item deleted successfully!");

    }

    private User getCurrentUser() {
        return userRepository.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new RuntimeException("No user Authenticated!"));
    }
}
