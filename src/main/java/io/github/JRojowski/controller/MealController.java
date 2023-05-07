package io.github.JRojowski.controller;

import io.github.JRojowski.entity.Meal;
import io.github.JRojowski.entity.Recipe;
import io.github.JRojowski.entity.dto.MealDto;
import io.github.JRojowski.service.MealService;
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
@RequestMapping("/meals")
@Slf4j
class MealController {

    private final MealService mealService;

    @PostMapping()
    ResponseEntity<Meal> createMeal(@RequestBody @Valid MealDto mealDto) {
        log.info("Consumed POST /meals");
        Meal meal = mealService.createMeal(mealDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(meal.getId())
                .toUri();
        return ResponseEntity.created(location).body(meal);
    }

    @PostMapping("/{id}/addRecipe")
    ResponseEntity<Recipe> createRecipe(@PathVariable int id,
                                        @RequestParam int foodId,
                                        @RequestParam double grams) {
        log.info("CONSUMED POST /meals/%s/addRecipe with params foodId: %s and grams: %s"
                .formatted(id, foodId, grams));
        return ResponseEntity.ok(mealService.createRecipe(id, foodId, grams));
    }

    @GetMapping
    ResponseEntity<List<MealDto>> getAllMeals() {
        log.info("CONSUMED GET /meals");
        return ResponseEntity.ok(mealService.getAllMeals());
    }

    @GetMapping("/{id}")
    ResponseEntity<MealDto> getMealById(@PathVariable int id) {
        log.info("CONSUMED GET /meals/%s".formatted(id));
        return ResponseEntity.ok(mealService.getMealById(id));
    }
    @PutMapping()
    ResponseEntity<MealDto> renameMeal(@RequestBody @Valid MealDto mealDto) {
        log.info("CONSUMED PUT /meals");
        return ResponseEntity.ok(mealService.editMeal(mealDto));
    }

}
