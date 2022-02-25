package io.github.JRojowski.controller;

import io.github.JRojowski.logic.FoodService;
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
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/foods")
class FoodController {

    public static final Logger logger = LoggerFactory.getLogger(FoodController.class);
    private final FoodRepository repository;
    private final FoodService service;

    FoodController(final FoodRepository repository, final FoodService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping
    ResponseEntity<Food> createFood(@RequestBody @Valid Food toCreate) {
        Food result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getFoodId())).body(result);
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    CompletableFuture<ResponseEntity<List<Food>>> readAllFoods() {
        logger.warn("Exposing all the foods!");
        return service.findAllAsync().thenApply(ResponseEntity::ok);
    }

    @GetMapping
    ResponseEntity<List<Food>> readAllFoods(Pageable page) {
        logger.info("Custom pageable");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @GetMapping("/{id}")
    ResponseEntity<Food> readFoodById(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/findByMealId/{id}")
    ResponseEntity<List<Food>> readFoodsFromMeal(@PathVariable int id) {
        return ResponseEntity.ok(repository.findFoodsAssociatedWithTheMealById(id));
    }

    @GetMapping("/search/reported")
    ResponseEntity<List<Food>> readReportedFoods(@RequestParam(defaultValue = "true") boolean state) {
        return ResponseEntity.ok(repository.findByReported(state));
    }

    @PutMapping("/{id}")
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

    @PatchMapping("/{id}")
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
