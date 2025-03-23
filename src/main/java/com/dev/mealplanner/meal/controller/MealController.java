package com.dev.mealplanner.meal.controller;

import com.dev.mealplanner.meal.domain.MealTO;
import com.dev.mealplanner.meal.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("meals")
@RequiredArgsConstructor
public class MealController {

    private MealService mealService;

    @GetMapping("getMeals")
    public ResponseEntity<List<MealTO>> getMealsByUser() {
        return ResponseEntity.ok(mealService.getMealsByUser());
    }

    @PostMapping("addMeal")
    public ResponseEntity<Map<String,String>> addMeal(@RequestBody() MealTO to) {
        return ResponseEntity.ok(mealService.addMeal(to));
    }

    @DeleteMapping("deleteMeal/{mealId}")
    public ResponseEntity<Map<String,String>> deleteMeal(@PathVariable("mealId") Long mealId) {
        return ResponseEntity.ok(mealService.deleteMeal(mealId));
    }

    @DeleteMapping("deleteItem")
    public ResponseEntity<Map<String,String>> deleteItem(@RequestParam("mealId") Long mealId, @RequestParam("itemId") Long itemId) {
        return  ResponseEntity.ok(mealService.deleteItem(mealId, itemId));
    }

}
