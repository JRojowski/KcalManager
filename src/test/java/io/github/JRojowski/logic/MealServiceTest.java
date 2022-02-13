package io.github.JRojowski.logic;

import io.github.JRojowski.model.MealRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class MealServiceTest {

    @Test
    @DisplayName("Should create a new meal from meals")
    void createMealCreatesAndSavesNewMeal() {
        // given
        var mockMealRepository = mock(MealRepository.class);
        // system under test
        var toTest = new MealService(mockMealRepository, null, null);

        // when
        //toTest.createMeal( )
        // then
    }

}