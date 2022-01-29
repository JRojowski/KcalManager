package io.github.JRojowski.adapter;

import io.github.JRojowski.model.Meal;
import io.github.JRojowski.model.MealRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlMealRepository extends MealRepository, JpaRepository<Meal, Integer> {

    //@Override
    //@Query("select distinct r from Meal m join fetch m.recipes r join fetch r.food")
    //List<Meal> findAll();
}
