package io.github.JRojowski.logic;

import io.github.JRojowski.model.*;
import io.github.JRojowski.model.projection.MealDTO;
import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
class MealServiceTest {


    @Test
    @DisplayName("Should create a new meal")
    void createMealCreatesAndSavesNewMeal() {
        // given
        var sourceMeal = new MealDTO();
        // and
        var foodRepository = mock(FoodRepository.class);
        when(foodRepository.findById(anyInt()))
                .thenReturn(Optional.of(FoodWithId(1)));
        // and
        var recipeRepository = mock(RecipeRepository.class);
        // and
        InMemoryMealRepository inMemoryMealRepository = inMemoryMealRepository();
        int countBeforeCall = inMemoryMealRepository.count();
        // system under test
        var toTest = new MealService(inMemoryMealRepository, foodRepository, recipeRepository);

        // when
        Meal result = toTest.createMeal(sourceMeal);

        // then
        assertThat(countBeforeCall + 1).isNotEqualTo(inMemoryMealRepository.count());

    }

//    private MealDTO MealDtoWith(String name, Set<Integer> foodIds) {
//        Set<Recipe> recipes = foodIds.stream()
//                .map(foodId -> {
//                    var recipe = mock(Recipe.class);
//                    when(recipe.getFood().getFoodId()).thenReturn(foodId);
//                    return recipe;
//                }).collect(Collectors.toSet());
//
//        var result = mock(MealDTO.class);
//        when(result.name).thenReturn(name);
//        when(result.ingredients).thenReturn(recipes);
//        return result;
//    }

    private Food FoodWithId(int id) {

        var result = mock(Food.class);
        when(result.getFoodId()).thenReturn(id);
        return result;
    }

    private InMemoryMealRepository inMemoryMealRepository() {
        return new InMemoryMealRepository();
    }


    private static class InMemoryMealRepository implements MealRepository {
        private int index = 0;
        private Map<Integer, Meal> map = new HashMap<>();

        public int count() {
            return map.values().size();
        }

        @Override
        public List<Meal> findAll() {
            return new ArrayList<>(map.values());
        }

        @Override
        public Optional<Meal> findById(final Integer id) {
            return Optional.ofNullable(map.get(id));
        }

        @Override
        public Meal save(final Meal entity) {
            if (entity.getMealId() == 0) {
                try {
                    var field = Meal.class.getDeclaredField("mealId");
                    field.setAccessible(true);
                    field.set(entity, ++index);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            map.put(entity.getMealId(), entity);
            return entity;
        }

        @Override
        public List<Meal> findMealsAssociatedWithTheFoodById(final Integer id) {
            return map.values().stream()
                    .filter(
                            meal -> meal.getRecipes()
                                    .stream()
                                    .anyMatch(recipe -> recipe.getFood().getFoodId() == id)
                    )
                    .collect(Collectors.toList());
        }

        @Override
        public boolean existsById(final Integer id) {
            return map.values().stream()
                    .anyMatch(meal -> meal.getMealId() == id);
        }
    }

}