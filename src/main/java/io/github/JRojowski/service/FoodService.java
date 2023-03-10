package io.github.JRojowski.service;

import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.dto.FoodCaloriesDto;
import io.github.JRojowski.entity.dto.FoodDto;
import io.github.JRojowski.repository.FoodRepository;
import io.github.JRojowski.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodService {

    public final FoodRepository foodRepository;
    public final Mapper mapper;

    public Food createNewFood(FoodDto foodDto) {
        return foodRepository.save(mapper.foodFromDto(foodDto));
    }

    public List<FoodDto> getAllFoods() {
        return foodRepository.findAll()
                .stream()
                .map(mapper::dtoFromFood)
                .collect(Collectors.toList());
    }

    public FoodDto getFoodById(int id) {
       return foodRepository.findById(id)
               .map(mapper::dtoFromFood)
               .orElseThrow(() -> new NotFoundException("Food with id: " + id + " not found"));
    }

    public Food reportFood(int id) {
        Food foodToUpdate = foodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Food with id: " + id + " not found"));
        foodToUpdate.setReported(!foodToUpdate.getReported());
        return foodRepository.save(foodToUpdate);
    }

    public FoodCaloriesDto countFoodCalories(int id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Food with id: " + id + " not found"));
        return FoodCaloriesDto.builder()
                .name(food.getName())
                .portion(food.getPortion())
                .kcal(food.getKcal() * food.getPortion() / 100)
                .protein(food.getProtein() * food.getPortion() / 100)
                .fat(food.getFat() * food.getPortion() / 100)
                .carbs(food.getCarbs() * food.getPortion() / 100)
                .build();
    }
}
