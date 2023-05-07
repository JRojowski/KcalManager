package io.github.JRojowski.service;

import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.Recipe;
import io.github.JRojowski.entity.dto.FoodDto;
import io.github.JRojowski.entity.dto.MealDto;
import io.github.JRojowski.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodService {
    public final FoodRepository foodRepository;
    public final ModelMapper modelMapper;
    private Environment environment;

    public Food createFood(FoodDto foodDto) {
        Food food = modelMapper.map(foodDto, Food.class);
        return foodRepository.save(food);
    }

    public List<FoodDto> getAllFoods() {
        return foodRepository.findAll()
                .stream()
                .map(food -> modelMapper.map(food, FoodDto.class))
                .map(foodDto -> {
                    foodDto.setEnvironment(environment.getProperty("local.server.port"));
                    return foodDto;
                })
                .collect(Collectors.toList());
    }

    public FoodDto getFoodById(int id) {
        FoodDto foodDto = foodRepository.findById(id)
                .map(food -> modelMapper.map(food, FoodDto.class))
                .orElseThrow(() -> new NotFoundException("Food with id: " + id + " not found"));
        String port = environment.getProperty("local.server.port");
        foodDto.setEnvironment(port);
        return foodDto;
    }


    public FoodDto reportFood(int id) {
        Food foodToUpdate = foodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Food with id: " + id + " not found"));
        foodToUpdate.setReported(!foodToUpdate.getReported());
        return modelMapper.map(foodRepository.save(foodToUpdate), FoodDto.class);
    }

    public List<MealDto> getMealsFromFood(int id) {
        return foodRepository.findById(id)
                    .orElseThrow()
                    .getRecipes().stream()
                    .map(Recipe::getMeal)
                    .map(meal -> modelMapper.map(meal, MealDto.class))
                    .toList();
    }
}
