package io.github.JRojowski.service;

import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.Meal;
import io.github.JRojowski.entity.Recipe;
import io.github.JRojowski.entity.dto.FoodDto;
import io.github.JRojowski.entity.dto.MealDto;
import io.github.JRojowski.repository.FoodRepository;
import io.github.JRojowski.util.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {

    private static final String NAME = "Serek wiejski";

    @Spy
    private Mapper mapper;
    @Mock
    private FoodRepository foodRepository;
    @InjectMocks
    private FoodService foodService;


    @Test
    void shouldSaveMFood() {
        FoodDto foodDto = FoodDto.builder()
                .name(NAME)
                .build();

        foodService.createNewFood(foodDto);

        verify(foodRepository, times(1)).save(any());
    }

    void getAllFoods() {
        Food food = Food.builder()
                .id(1)
                .name(NAME)
                .build();
        Food food2 = Food.builder()
                .id(2)
                .name("Pizza")
                .build();

        when(foodRepository.findAll())
                .thenReturn(List.of(food, food2));

        List<FoodDto> result = foodService.getAllFoods();

        assertThat(result).isNotNull().hasSize(2);
        assertThat(result.get(1)).isInstanceOf(FoodDto.class);
        assertThat(result.get(0).getName()).isEqualTo(NAME);
    }

    @Test
    void getFoodByIdShouldThrowException() {
        when(foodRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> foodService.getFoodById(8))
                .isInstanceOf(NotFoundException.class);
    }


    void shouldGetFoodDtoById() {
        Food food = Food.builder().id(1).name("Food").producer("Producer").price(1.99)
                .portion(200).kcal(100).protein(20.0).fat(5.4).carbs(10.2).build();

        when(foodRepository.findById(1))
                .thenReturn(Optional.of(food));

        FoodDto result = foodService.getFoodById(1);

        assertThat(result).isNotNull().isInstanceOf(FoodDto.class);
        assertThat(result.getId()).isEqualTo(food.getId());
        assertThat(result.getName()).isEqualTo(food.getName());
        assertThat(result.getProducer()).isEqualTo(food.getProducer());
        assertThat(result.getPrice()).isEqualTo(food.getPrice());
        assertThat(result.getPortion()).isEqualTo(food.getPortion());
        assertThat(result.getKcal()).isEqualTo(food.getKcal());
        assertThat(result.getProtein()).isEqualTo(food.getProtein());
        assertThat(result.getFat()).isEqualTo(food.getFat());
        assertThat(result.getCarbs()).isEqualTo(food.getCarbs());
    }

    @Test
    void reportFoodShouldThrowException() {
        when(foodRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> foodService.reportFood(8))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void shouldReportFood() {
        Food food = Food.builder().id(1).reported(false).build();

        when(foodRepository.findById(1))
                .thenReturn(Optional.of(food));

        foodService.reportFood(1);

        verify(foodRepository).findById(1);
        verify(foodRepository).save(food);
    }

    @Test
    void getMealsFromFoodThrowsException() {
        when(foodRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> foodService.getMealsFromFood(1))
                .isInstanceOf(NoSuchElementException.class);
    }
    @Test
    void getMealsFromFood() {
        Food food = new Food();
        Meal meal = Meal.builder().id(1).name("Meal").build();
        Meal meal2 = Meal.builder().id(2).name("Meal2").build();
        Recipe recipe = new Recipe();
        recipe.setMeal(meal);
        recipe.setFood(food);
        Recipe recipe2 = new Recipe();
        recipe2.setMeal(meal2);
        recipe2.setFood(food);
        food.setRecipes(Set.of(recipe, recipe2));

        when(foodRepository.findById(anyInt()))
                .thenReturn(Optional.of(food));

        List<MealDto> result = foodService.getMealsFromFood(1);

        assertThat(result).isNotNull().isNotEmpty().hasSize(2);

    }
}