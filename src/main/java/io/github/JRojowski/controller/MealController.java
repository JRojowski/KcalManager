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

import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/meal")
@Slf4j
class MealController {

    private final MealService mealService;

    @PostMapping()
    ResponseEntity<Integer> createFood(@RequestParam @NotBlank String name) {
        Meal meal = mealService.createMeal(name);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{}")
                .buildAndExpand(meal.getId())
                .toUri();
        return ResponseEntity.created(location).body(meal.getId());
    }

    @PostMapping("/{id}/addRecipe")
    ResponseEntity<Recipe> createRecipe(@PathVariable int id,
                                        @RequestParam int foodId,
                                        @RequestParam double grams) {

        return ResponseEntity.ok(mealService.createRecipe(id, foodId, grams));
    }

    @GetMapping
    ResponseEntity<List<MealDto>> getAllMeals() {
        return ResponseEntity.ok(mealService.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<MealDto> getMealById(int id) {
        return ResponseEntity.ok(mealService.getDtoById(id));
    }
    @PutMapping("/{id}/rename")
    ResponseEntity<MealDto> renameMeal(@PathVariable int id,
                                        @RequestParam String name) {
        return ResponseEntity.ok(mealService.editMeal(id, name));

    }
}
