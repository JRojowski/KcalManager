package io.github.JRojowski.service;

import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.dto.FoodDto;
import io.github.JRojowski.repository.FoodRepository;
import io.github.JRojowski.util.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FoodServiceTest {

    private static final String NAME = "Serek wiejski";

    @Mock
    private Mapper mapper;
    @Mock
    private FoodRepository foodRepository;
    @InjectMocks
    private FoodService foodService;


    @Test
    void shouldSaveMeal() {
        FoodDto foodDto = FoodDto.builder()
                .name(NAME)
                .build();
        Food food = Food.builder()
                .name(NAME)
                .build();

        Mockito
                .when(mapper.foodFromDto(foodDto))
                .thenReturn(food);

        foodService.createNewFood(foodDto);

        verify(foodRepository, times(1)).save(food);
    }

    @Test
    void getAllFoods() {
        Food food = Food.builder()
                .name(NAME)
                .build();
        Food food2 = Food.builder()
                .name("Pizza")
                .build();
        FoodDto foodDto = FoodDto.builder()
                .name(NAME)
                .build();
        FoodDto foodDto2 = FoodDto.builder()
                .name("Pizza")
                .build();

        Mockito
                .when(mapper.dtoFromFood(food))
                .thenReturn(foodDto);
        Mockito
                .when(mapper.dtoFromFood(food2))
                .thenReturn(foodDto2);
        Mockito
                .when(foodRepository.findAll())
                .thenReturn(List.of(food, food2));

        List<FoodDto> result = foodService.getAllFoods();

        assertThat(result).isNotNull().hasSize(2);
        assertThat(result.get(0)).isInstanceOf(FoodDto.class);
        assertThat(result.get(0).getName()).isEqualTo(NAME);

    }

    @Test
    void getFoodById() {
    }
}