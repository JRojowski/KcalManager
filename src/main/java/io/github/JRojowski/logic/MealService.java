package io.github.JRojowski.logic;

import io.github.JRojowski.model.*;
import io.github.JRojowski.model.projection.MealDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MealService {

    private MealRepository repository;
    private FoodRepository foodRepository;
    private RecipeRepository recipeRepository;

    MealService(final MealRepository repository, final FoodRepository foodRepository, final RecipeRepository recipeRepository) {
        this.repository = repository;
        this.foodRepository = foodRepository;
        this.recipeRepository = recipeRepository;
    }
//to do puscic z idikiem wyjebanym i stestowac bugiem
    public Meal createMeal(MealDTO source) {
        var newMeal = new Meal();
        newMeal.setName(source.name);
            newMeal.getRecipes().addAll(source.ingredients.stream()
                    .map(recipe -> {
                        Food food = foodRepository.findById(recipe.getFood().getFoodId()).orElse(null);
                        Recipe newRecipe = new Recipe();
                        newRecipe.setFood(food);
                        newRecipe.setMeal(newMeal);
                        newRecipe.setGrams(recipe.getGrams());
                        return newRecipe;
                    }).collect(Collectors.toSet())
            );
        return repository.save(newMeal);
    }

    public List<Meal> readAll() {
        return repository.findAll();
    }

    public void addIngredient(int mealId, int foodId, int grams) {
        if(!recipeRepository.existsByFoodIdAndMealId(foodId, mealId)) {
            Food food = foodRepository.findById(foodId).get();
            Meal meal = repository.findById(mealId).get();
            Recipe newRecipe = new Recipe();
            newRecipe.setFood(food);
            newRecipe.setMeal(meal);
            newRecipe.setGrams(grams);

            meal.getRecipes().add(newRecipe);
            repository.save(meal);
        }
    }

}
