package io.github.JRojowski.util;

import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.Meal;
import io.github.JRojowski.entity.dto.FoodDto;
import io.github.JRojowski.entity.dto.MealDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Food foodFromDto(FoodDto foodDto) {
        return Food.builder()
                .name(foodDto.getName())
                .producer(foodDto.getProducer())
                .price(foodDto.getPrice())
                .portion(foodDto.getPortion())
                .kcal(foodDto.getKcal())
                .protein(foodDto.getProtein())
                .fat(foodDto.getFat())
                .carbs(foodDto.getCarbs())
                .build();
    }

    public FoodDto dtoFromFood(Food food) {
        return FoodDto.builder()
                .name(food.getName())
                .producer(food.getProducer())
                .price(food.getPrice())
                .portion(food.getPortion())
                .kcal(food.getKcal())
                .protein(food.getProtein())
                .fat(food.getFat())
                .carbs(food.getCarbs())
                .build();
    }

    public Meal mealFromDto(MealDto mealDto) {
        return Meal.builder()
                .name(mealDto.getName())
                .build();
    }

    public MealDto dtoFromMeal(Meal meal) {
        return MealDto.builder()
                .name(meal.getName())
                .build();
    }
}
