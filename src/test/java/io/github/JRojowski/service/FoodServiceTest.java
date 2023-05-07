package io.github.JRojowski.service;

import io.github.JRojowski.config.ModelMapperTestConfig;
import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.Meal;
import io.github.JRojowski.entity.Recipe;
import io.github.JRojowski.entity.dto.FoodDto;
import io.github.JRojowski.entity.dto.MealDto;
import io.github.JRojowski.repository.FoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.test.util.ReflectionTestUtils;
import org.webjars.NotFoundException;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {
    @Mock
    private FoodRepository foodRepository;
    @Mock
    private Environment environment;
    @InjectMocks
    private FoodService foodService;
    @Captor
    private ArgumentCaptor<Food> foodCaptor;

    private static final String NAME = "Serek wiejski";
    private static final Integer ID = 1;

    @BeforeEach
    public void setUp() {
        ModelMapperTestConfig modelMapperTestConfig = new ModelMapperTestConfig();
        ModelMapper modelMapper = modelMapperTestConfig.modelMapper();
        ReflectionTestUtils.setField(foodService, "modelMapper", modelMapper);
    }

    @Test
    void shouldSaveFood() {
        FoodDto foodDto = FoodDto.builder()
                .name(NAME)
                .build();

        foodService.createFood(foodDto);

        verify(foodRepository, times(1)).save(any());
    }

    @Test
    void getAllFoods() {
        Food food = createFood();
        Food food2 = createFood();
        Recipe recipe = new Recipe();
        Meal meal = new Meal(1, "Name of Meal", Set.of(recipe));
        recipe.setMeal(meal);
        food.getRecipes().add(recipe);

        when(foodRepository.findAll())
                .thenReturn(List.of(food, food2));
        when(environment.getProperty("local.server.port"))
                .thenReturn("8000");

        List<FoodDto> result = foodService.getAllFoods();

        assertThat(result).isNotNull().hasSize(2);
        assertThat(result.get(0).getMealList()).isNotEmpty().hasSize(1).containsExactly("Name of Meal");
        assertThat(result.get(1).getName()).isEqualTo(NAME);
    }

    @Test
    void getFoodByIdShouldThrowException() {
        when(foodRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> foodService.getFoodById(8))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void shouldGetFoodDtoById() {
        Food food = createFood();
        Recipe recipe = new Recipe();
        Meal meal = new Meal(1, "Name of Meal", Set.of(recipe));
        recipe.setMeal(meal);
        food.getRecipes().add(recipe);

        when(foodRepository.findById(1))
                .thenReturn(Optional.of(food));

        FoodDto result = foodService.getFoodById(1);

        assertThat(result.getName()).isEqualTo(food.getName());
        assertThat(result.getMealList()).isNotEmpty().hasSize(1).containsExactly("Name of Meal");
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
        Food food = createFood();
        food.setReported(true);

        when(foodRepository.findById(1))
                .thenReturn(Optional.of(food));
        when(foodRepository.save(foodCaptor.capture()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        FoodDto reportedFood = foodService.reportFood(1);

        verify(foodRepository).save(food);
        assertEquals(false, foodCaptor.getValue().getReported());
        assertEquals(false, reportedFood.isReported());
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
        Food food = createFood();
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

    private Food createFood() {
        Food food = new Food();
        food.setId(ID);
        food.setName(NAME);
        food.setRecipes(new HashSet<>());
        return food;
    }
}