package io.github.JRojowski.service;

import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.Recipe;
import io.github.JRojowski.entity.dto.FoodDto;
import io.github.JRojowski.entity.dto.MealDto;
import io.github.JRojowski.repository.FoodRepository;
import io.github.JRojowski.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodService {

    public final FoodRepository foodRepository;
    public final Mapper mapper;
    @Autowired
    private Environment environment;

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
        FoodDto foodDto = foodRepository.findById(id)
                .map(mapper::dtoFromFood)
                .orElseThrow(() -> new NotFoundException("Food with id: " + id + " not found"));
        String port = environment.getProperty("local.server.port");
        foodDto.setEnvironment(port);
        return foodDto;
    }

    @Transactional
    public Food reportFood(int id) {
        Food foodToUpdate = foodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Food with id: " + id + " not found"));
        foodToUpdate.setReported(!foodToUpdate.getReported());
        return foodRepository.save(foodToUpdate);
    }

    public List<MealDto> getMealsFromFood(int id) {
        return foodRepository.findById(id)
                    .orElseThrow()
                    .getRecipes().stream()
                    .map(Recipe::getMeal)
                    .map(mapper::dtoFromMeal)
                    .toList();
    }
}
