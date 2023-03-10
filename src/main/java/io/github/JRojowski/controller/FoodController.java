package io.github.JRojowski.controller;

import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.dto.FoodCaloriesDto;
import io.github.JRojowski.entity.dto.FoodDto;
import io.github.JRojowski.service.FoodService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/foods")
class FoodController {

    public static final Logger logger = LoggerFactory.getLogger(FoodController.class);
    private final FoodService foodService;

    @PostMapping
    ResponseEntity<Food> createFood(@Valid @RequestBody FoodDto foodDto) {
        Food food = foodService.createNewFood(foodDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(food.getId())
                .toUri();
        return ResponseEntity.created(location).body(food);
    }

    @GetMapping
    ResponseEntity<List<FoodDto>> getAllFoods() {
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    @GetMapping("/{id}")
    ResponseEntity<FoodDto> getFoodById(@PathVariable int id) {
        return ResponseEntity.ok(foodService.getFoodById(id));
    }

    @PatchMapping("/report/{id}")
    ResponseEntity<Food> reportFood(@PathVariable int id) {
        return ResponseEntity.ok(foodService.reportFood(id));
    }

    @GetMapping("/calories/{id}")
    ResponseEntity<FoodCaloriesDto> countFoodCalories(@PathVariable int id) {
        return ResponseEntity.ok(foodService.countFoodCalories(id));
    }


}
