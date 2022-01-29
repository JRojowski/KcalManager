package io.github.JRojowski.adapter;

import io.github.JRojowski.model.Meal;
import io.github.JRojowski.model.Recipe;
import io.github.JRojowski.model.RecipeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlRecipeRepository extends RecipeRepository, JpaRepository<Recipe, Integer> {
    //@Override
    //@Query("select distinct r from Recipe m join fetch m.food r join fetch r.meal")
    //List<Recipe> findAll();
}
