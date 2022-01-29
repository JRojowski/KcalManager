package io.github.JRojowski.adapter;

import io.github.JRojowski.model.Food;
import io.github.JRojowski.model.FoodRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface SqlFoodRepository extends FoodRepository, JpaRepository<Food, Integer> {

    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from foods where food_id=:id")
    boolean existsById(@Param("id") Integer id);

    @Override
    boolean existsByReportedIsTrue();
}
