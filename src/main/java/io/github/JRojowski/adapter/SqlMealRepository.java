package io.github.JRojowski.adapter;

import io.github.JRojowski.model.Meal;
import io.github.JRojowski.model.MealRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlMealRepository extends MealRepository, JpaRepository<Meal, Integer> {

    @Override
    @Query(value = "select distinct meal from Meal meal join meal.recipes recipes join recipes.food food where food.foodId=:id")
    List<Meal> findMealsAssociatedWithTheFoodById(@Param("id") Integer id);
}
