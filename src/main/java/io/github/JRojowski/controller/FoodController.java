package io.github.JRojowski.controller;

import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.dto.FoodDto;
import io.github.JRojowski.entity.dto.MealDto;
import io.github.JRojowski.service.FoodService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/foods")
@Slf4j
class FoodController {

    private final FoodService foodService;

    @PostMapping
    ResponseEntity<Food> createFood(@Valid @RequestBody FoodDto foodDto) {
        log.info("Consumed POST /foods");
        Food food = foodService.createFood(foodDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(food.getId())
                .toUri();
        return ResponseEntity.created(location).body(food);
    }

    @GetMapping
    ResponseEntity<List<FoodDto>> getAllFoods() {
        log.info("CONSUMED GET /foods");
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    @GetMapping("/{id}")
    ResponseEntity<FoodDto> getFoodById(@PathVariable int id) {
        log.info("CONSUMED GET /foods/%s".formatted(id));
        return ResponseEntity.ok(foodService.getFoodById(id));
    }

    @PatchMapping("/report/{id}")
    ResponseEntity<FoodDto> reportFood(@PathVariable int id) {
        log.info("CONSUMED PATCH /foods/report/%s".formatted(id));
        return ResponseEntity.ok(foodService.reportFood(id));
    }

    @GetMapping("/{id}/meals")
    ResponseEntity<List<MealDto>> getMealsfromFood(@PathVariable int id) {
        log.info("CONSUMED GET /foods/%s/meals".formatted(id));
        return ResponseEntity.ok(foodService.getMealsFromFood(id));
    }

}
