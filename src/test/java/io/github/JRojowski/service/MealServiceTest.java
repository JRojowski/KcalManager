package io.github.JRojowski.service;

import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.Meal;
import io.github.JRojowski.entity.Recipe;
import io.github.JRojowski.repository.FoodRepository;
import io.github.JRojowski.repository.MealRepository;
import io.github.JRojowski.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MealServiceTest {

    @Mock
    private MealRepository mealRepository;
    @Mock
    private FoodRepository foodRepository;
    @Mock
    private RecipeRepository recipeRepository;
    @InjectMocks
    private MealService mealService;
    @Captor
    private ArgumentCaptor<Recipe> recipeCaptor;


    @Test
    void createRecipe() {
        Meal meal = Meal.builder().id(1).name("Meal").build();
        Food food = Food.builder().id(1).name("Food").build();

        when(mealRepository.findById(1))
                .thenReturn(Optional.of(meal));
        when(foodRepository.findById(1))
                .thenReturn(Optional.of(food));

        mealService.createRecipe(1,1, 200);

        verify(recipeRepository).save(recipeCaptor.capture());
        Recipe capturedRecipe = recipeCaptor.getValue();

        assertThat(capturedRecipe).isInstanceOf(Recipe.class);
        assertThat(capturedRecipe.getMeal()).isEqualTo(meal);
        assertThat(capturedRecipe.getFood()).isEqualTo(food);
        assertThat(capturedRecipe.getGrams()).isEqualTo(200);


    }
}