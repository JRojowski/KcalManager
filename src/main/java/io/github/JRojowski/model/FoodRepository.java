package io.github.JRojowski.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FoodRepository {

    List<Food> findAll();

    Page<Food> findAll(Pageable page);

    Optional<Food> findById(Integer id);

    boolean existsById(Integer id);

    boolean existsByReportedIsTrue();

    List<Food> findByProteinGreaterThanEqual(@Param("protein") Double protein);

    Food save(Food entity);

}
