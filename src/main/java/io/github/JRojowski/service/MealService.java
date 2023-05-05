package io.github.JRojowski.service;

import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.Meal;
import io.github.JRojowski.entity.Recipe;
import io.github.JRojowski.entity.dto.MealDto;
import io.github.JRojowski.repository.FoodRepository;
import io.github.JRojowski.repository.MealRepository;
import io.github.JRojowski.repository.RecipeRepository;
import io.github.JRojowski.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MealService {

    private final Mapper mapper;
    private final MealRepository mealRepository;
    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;

    public Meal createMeal(String name) {
        Meal meal = Meal.builder().name(name).build();
        return mealRepository.save(meal);
    }

    public Recipe createRecipe(int mealId, int foodId, double grams) {
        Meal meal = mealRepository.findById(mealId).orElseThrow();
        Food food = foodRepository.findById(foodId).orElseThrow();
        Recipe recipe = new Recipe();
        recipe.setMeal(meal);
        recipe.setFood(food);
        recipe.setGrams(grams);
        return recipeRepository.save(recipe);
    }
    public List<MealDto> getAll() {
        return mealRepository.findAll().stream().map(mapper::dtoFromMeal).toList();
    }
    public MealDto getDtoById(int id) {
        return mealRepository.findById(id)
                .map(mapper::dtoFromMeal)
                .orElseThrow();
    }

    public Meal getById(int id) {
        return mealRepository.findById(id)
                .orElseThrow();
    }

    public MealDto editMeal(int id, String name) {
        Meal editedMeal = getById(id);
        editedMeal.setName(name);
        return mapper.dtoFromMeal(mealRepository.save(editedMeal));
    }
}
