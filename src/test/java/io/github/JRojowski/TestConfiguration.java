package io.github.JRojowski;

import io.github.JRojowski.model.Food;
import io.github.JRojowski.model.FoodRepository;
import io.github.JRojowski.model.Meal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Driver;
import java.util.*;

@Configuration
class TestConfiguration {

    @Bean
    @Primary
    @Profile("!integration")
    DataSource e2eTestDataSource() {
        var result = new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        result.setDriverClassName("org.h2.Driver");
        return result;
    }

    @Bean
    @Primary
    @Profile("integration")
    FoodRepository foodRepo() {
        return new FoodRepository() {
            private Map<Integer, Food> foods = new HashMap<>();

            @Override
            public List<Food> findAll() {
                return new ArrayList<>(foods.values());
            }

            @Override
            public Page<Food> findAll(final Pageable page) {
                return null;
            }

            @Override
            public Optional<Food> findById(final Integer id) {
                return Optional.ofNullable(foods.get(id));
            }

            @Override
            public boolean existsById(final Integer id) {
                return foods.containsKey(id);
            }

            @Override
            public boolean existsByReportedIsTrue() {
                return false;
            }

            @Override
            public List<Food> findByProteinGreaterThanEqual(final Double protein) {
                return null;
            }

            @Override
            public Food save(final Food entity) {
                int key = foods.size() + 1;
                try {
                    var field = Food.class.getDeclaredField("foodId");
                    field.setAccessible(true);
                    field.set(entity, key);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                foods.put(key, entity);
                return foods.get(key);
            }

            @Override
            public List<Food> findFoodsAssociatedWithTheMealById(final Integer id) {
                return null;
            }
        };
    }
}
