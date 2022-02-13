package io.github.JRojowski.controller;

import io.github.JRojowski.logic.MealService;
import io.github.JRojowski.model.Food;
import io.github.JRojowski.model.Meal;
import io.github.JRojowski.model.MealRepository;
import io.github.JRojowski.model.projection.MealDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
class MealController {

    public static final Logger logger = LoggerFactory.getLogger(MealController.class);
    private final MealRepository repository;
    private final MealService service;

    MealController(final MealRepository repository, final MealService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping(value = "/meals")
    ResponseEntity<Meal> createMeal(@RequestBody @Valid MealDTO toCreate) {
        Meal result = service.createMeal(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getMealId())).body(result);
    }

    @GetMapping(value = "/meals", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Meal>> readAllMeals() {
        logger.warn("Exposing all the meals");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(value = "/meals/{id}")
    ResponseEntity<Meal> readMealById(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/mealsfromfoods/{id}")
    ResponseEntity<List<Meal>> readMealsFromFood(@PathVariable int id) {
        return ResponseEntity.ok(repository.findMealsAssociatedWithTheFoodById(id));
    }

    @PutMapping(value = "/addIngredient")
    ResponseEntity<?> addIngredient(@RequestParam int meal, @RequestParam int food, @RequestParam int grams) {
        service.addIngredient(meal, food, grams);
        return ResponseEntity.noContent().build();
    }
}
