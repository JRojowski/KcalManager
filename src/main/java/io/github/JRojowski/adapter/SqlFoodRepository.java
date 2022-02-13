package io.github.JRojowski.adapter;

import io.github.JRojowski.model.Food;
import io.github.JRojowski.model.FoodRepository;
import io.github.JRojowski.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlFoodRepository extends FoodRepository, JpaRepository<Food, Integer> {

    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from foods where food_id=:id")
    boolean existsById(@Param("id") Integer id);

    @Override
    boolean existsByReportedIsTrue();

    @Override
    @Query(value = "select distinct food from Food food join food.recipes recipes join recipes.meal meal where meal.mealId=:id")
    List<Meal> findFoodsAssociatedWithTheMealById(@Param("id") Integer id);
}
