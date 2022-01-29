package io.github.JRojowski.model;


import java.util.List;
import java.util.Optional;

public interface RecipeRepository {

    List<Recipe> findAll();

    Optional<Recipe> findById(Integer id);
}
