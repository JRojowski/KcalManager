package io.github.JRojowski.controller;

import io.github.JRojowski.entity.Meal;
import io.github.JRojowski.entity.dto.CaloriesDto;
import io.github.JRojowski.entity.dto.MealDto;
import io.github.JRojowski.service.MealService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/meals")
class MealController {

    public static final Logger logger = LoggerFactory.getLogger(MealController.class);
    private final MealService mealService;

    @PostMapping
    ResponseEntity<Meal> createNewMealAndFood(@Valid @RequestBody MealDto mealDto) {
        Meal meal = mealService.createNewMealAndFood(mealDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(meal.getMealId())
                .toUri();

        return ResponseEntity.created(location).body(meal);
    }

    @GetMapping("/calories/{id}")
    ResponseEntity<CaloriesDto> getMealCalories(@PathVariable int id) {
        return ResponseEntity.ok(mealService.getMealCalories(id));
    }


}
