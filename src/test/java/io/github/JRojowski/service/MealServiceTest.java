package io.github.JRojowski.service;

import io.github.JRojowski.config.ModelMapperTestConfig;
import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.Meal;
import io.github.JRojowski.entity.Recipe;
import io.github.JRojowski.entity.dto.MealDto;
import io.github.JRojowski.repository.FoodRepository;
import io.github.JRojowski.repository.MealRepository;
import io.github.JRojowski.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
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
    @Captor
    private ArgumentCaptor<Meal> mealCaptor;

    @BeforeEach
    public void setUp() {
        ModelMapperTestConfig modelMapperTestConfig = new ModelMapperTestConfig();
        ModelMapper modelMapper = modelMapperTestConfig.modelMapper();
        ReflectionTestUtils.setField(mealService, "modelMapper", modelMapper);
    }

    @Test
    void creatMeal() {
        MealDto mealDto = MealDto.builder().name("Name").build();
        when(mealRepository.save(mealCaptor.capture()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Meal savedMeal = mealService.createMeal(mealDto);
        assertEquals("Name", savedMeal.getName());
        assertEquals("Name", mealCaptor.getValue().getName());
    }

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

    @Test
    void getAllMeals() {
        Meal meal = Meal.builder().name("Meal").build();
        Meal meal2 = Meal.builder().name("Meal").build();

        when(mealRepository.findAll())
                .thenReturn(List.of(meal, meal2));

        List<MealDto> result = mealService.getAllMeals();

        assertThat(result).isNotNull().isNotEmpty().hasSize(2);
        assertEquals("Meal", result.stream().findAny().orElseThrow().getName());
    }

    @Test
    void getMealByIdShouldThrowException() {
        when(mealRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> mealService.getMealById(8))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void getMealById() {
        Meal meal = Meal.builder().id(1).name("Name").build();

        when(mealRepository.findById(1))
                .thenReturn(Optional.of(meal));

        MealDto result = mealService.getMealById(1);

        assertThat(result.getName()).isEqualTo("Name");
    }

    @Test
    void editMealShouldThrowException() {
        MealDto mealDto = MealDto.builder().id(1).build();
        when(mealRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> mealService.editMeal(mealDto))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void editMeal() {
        MealDto newMeal = MealDto.builder().id(1).name("New Name").build();
        Meal oldMeal = Meal.builder().id(1).name("Old Name").build();

        when(mealRepository.findById(anyInt()))
                .thenReturn(Optional.of(oldMeal));
        when(mealRepository.save(mealCaptor.capture()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        MealDto result = mealService.editMeal(newMeal);
        assertEquals("New Name", mealCaptor.getValue().getName());
        assertEquals("New Name", result.getName());

    }

}