package io.github.JRojowski.controller;

import io.github.JRojowski.model.Food;
import io.github.JRojowski.model.FoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
class FoodController {

    public static final Logger logger = LoggerFactory.getLogger(FoodController.class);
    private final FoodRepository repository;


    FoodController(final FoodRepository repository) {
        this.repository = repository;
    }


    @PostMapping(value = "/foods")
    ResponseEntity<Food> createFood(@RequestBody @Valid Food toCreate) {
        Food result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getFoodId())).body(result);
    }

    @GetMapping(value = "/foods", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Food>> readAllFoods() {
        logger.warn("Exposing all the foods!");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(value = "/foods")
    ResponseEntity<List<Food>> readAllFoods(Pageable page) {
        logger.info("Custom pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @GetMapping(value = "/foods/{id}")
    ResponseEntity<Food> readFoodById(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/foods/{id}")
    ResponseEntity<?> updateFood(@PathVariable int id, @RequestBody @Valid Food toUpdate) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(food -> {
                    food.updateFrom(toUpdate);
                    repository.save(food);
                });
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/foods/{id}")
    public ResponseEntity<?> reportFood(@PathVariable int id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(food -> {
                    food.setReported(!food.isReported());
                    repository.save(food);
                });
        return ResponseEntity.noContent().build();
    }
}
