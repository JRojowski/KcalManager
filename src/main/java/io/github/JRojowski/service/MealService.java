package io.github.JRojowski.service;

import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.Meal;
import io.github.JRojowski.entity.Recipe;
import io.github.JRojowski.repository.FoodRepository;
import io.github.JRojowski.repository.MealRepository;
import io.github.JRojowski.repository.RecipeRepository;
import io.github.JRojowski.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MealService {

    private final Mapper mapper;
    private final MealRepository mealRepository;
    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;


    public Recipe createRecipe(int mealId, int foodId, double grams) {
        Meal meal = mealRepository.findById(mealId).orElseThrow();
        Food food = foodRepository.findById(foodId).orElseThrow();
        Recipe recipe = new Recipe();
        recipe.setMeal(meal);
        recipe.setFood(food);
        recipe.setGrams(grams);
        return recipeRepository.save(recipe);
    }


}
