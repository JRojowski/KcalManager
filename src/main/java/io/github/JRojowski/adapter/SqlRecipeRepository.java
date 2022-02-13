package io.github.JRojowski.adapter;

import io.github.JRojowski.model.Meal;
import io.github.JRojowski.model.Recipe;
import io.github.JRojowski.model.RecipeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlRecipeRepository extends RecipeRepository, JpaRepository<Recipe, Integer> {

    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from recipes where food_id=:foodId and meal_id=:mealId")
    boolean existsByFoodIdAndMealId(@Param("foodId") Integer foodId, @Param("mealId") Integer mealId);
}
