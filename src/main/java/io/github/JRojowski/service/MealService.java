package io.github.JRojowski.service;

import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.Meal;
import io.github.JRojowski.entity.Recipe;
import io.github.JRojowski.entity.dto.MealDto;
import io.github.JRojowski.repository.FoodRepository;
import io.github.JRojowski.repository.MealRepository;
import io.github.JRojowski.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class MealService {

    private final ModelMapper modelMapper;
    private final MealRepository mealRepository;
    private final FoodRepository foodRepository;
    private final RecipeRepository recipeRepository;

    public Meal createMeal(MealDto mealDto) {
        Meal meal = modelMapper.map(mealDto, Meal.class);
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
    public List<MealDto> getAllMeals() {
        return mealRepository.findAll().stream()
                .map(meal -> modelMapper.map(meal, MealDto.class))
                .toList();
    }
    public MealDto getMealById(int id) {
        return mealRepository.findById(id)
                .map(meal -> modelMapper.map(meal, MealDto.class))
                .orElseThrow(() -> new NotFoundException("not"));
    }

    public MealDto editMeal(MealDto mealDto) {
        Meal editedMeal = mealRepository.findById(mealDto.getId())
                .orElseThrow(() -> new NotFoundException("not fiynd"));
        editedMeal.setName(mealDto.getName());
        return modelMapper.map(mealRepository.save(editedMeal), MealDto.class);
    }
}
