package io.github.JRojowski.service;

import io.github.JRojowski.entity.Meal;
import io.github.JRojowski.entity.Recipe;
import io.github.JRojowski.entity.dto.CaloriesDto;
import io.github.JRojowski.entity.dto.MealDto;
import io.github.JRojowski.repository.MealRepository;
import io.github.JRojowski.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class MealService {

    private final Mapper mapper;
    private final MealRepository mealRepository;

    public Meal createNewMealAndFood(MealDto mealDto) {
        Meal meal = mapper.mealFromDto(mealDto);
        if (!mealDto.getRecipeDtoList().isEmpty()) {
            Set<Recipe> newRecipes = new HashSet<>();
            mealDto.getRecipeDtoList()
                    .forEach(recipeDto -> {
                        Recipe recipe = new Recipe();
                        recipe.setFood(mapper.foodFromDto(recipeDto.getFoodDto()));
                        recipe.setMeal(meal);
                        recipe.setGrams(recipeDto.getGrams());
                        newRecipes.add(recipe);
                    });
            meal.setRecipes(newRecipes);
        }
        return mealRepository.save(meal);
    }

    public CaloriesDto getMealCalories(int id) {
        CaloriesDto caloriesDto = new CaloriesDto();
        mealRepository.findById(id)
                .ifPresent(meal -> {
                    caloriesDto.setName(meal.getName());
                    meal.getRecipes()
                            .forEach(recipe -> {
                                caloriesDto.setKcal(caloriesDto.getKcal()
                                                    + recipe.getFood().getKcal() * recipe.getGrams() / 100);
                                caloriesDto.setProtein(caloriesDto.getProtein()
                                                    + recipe.getFood().getProtein() * recipe.getGrams() / 100);
                                caloriesDto.setFat(caloriesDto.getFat()
                                                    + recipe.getFood().getFat() * recipe.getGrams() / 100);
                                caloriesDto.setCarbs(caloriesDto.getCarbs()
                                                    + recipe.getFood().getCarbs() * recipe.getGrams() / 100);
                            });
                });
        return caloriesDto;
    }










}