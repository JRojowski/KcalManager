package io.github.JRojowski.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlFoodRepository extends FoodRepository, JpaRepository<Food, Integer> {
}
