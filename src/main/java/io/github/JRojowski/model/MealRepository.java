package io.github.JRojowski.model;

import io.github.JRojowski.model.projection.MealWriteModel;

import java.util.List;
import java.util.Optional;

public interface MealRepository {

    List<Meal> findAll();

    Optional<Meal> findById(Integer id);

    Meal save(Meal entity);

    List<Meal> findMealsAssociatedWithTheFoodById(Integer id);

    boolean existsById(Integer id);
}
