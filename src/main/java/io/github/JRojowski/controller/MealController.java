package io.github.JRojowski.controller;

import io.github.JRojowski.entity.Recipe;
import io.github.JRojowski.service.MealService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/meal")
class MealController {

    public static final Logger logger = LoggerFactory.getLogger(MealController.class);
    private final MealService mealService;

    @PostMapping("{id}/addRecipe")
    ResponseEntity<Recipe> createRecipe(@PathVariable int id,
                                        @RequestParam int foodId,
                                        @RequestParam double grams) {

        return ResponseEntity.ok(mealService.createRecipe(id, foodId, grams));
    }

}
