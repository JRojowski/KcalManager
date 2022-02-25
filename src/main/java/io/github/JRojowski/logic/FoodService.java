package io.github.JRojowski.logic;

import io.github.JRojowski.model.Food;
import io.github.JRojowski.model.FoodRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class FoodService {

    private final FoodRepository repository;

    FoodService(final FoodRepository repository) {
        this.repository = repository;
    }

    @Async
    public CompletableFuture<List<Food>> findAllAsync() {
        return CompletableFuture.supplyAsync(repository::findAll);
    }
}
