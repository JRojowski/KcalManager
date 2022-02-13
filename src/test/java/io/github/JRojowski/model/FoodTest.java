package io.github.JRojowski.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    @Test
    void functionUpdateFromUpdatesFood() {

        //given
        Food foodToUpdate = new Food("Serek wiejski", 200);
        Food sourceFoodToUpdate = new Food("Serek wiejski", 500);

        //when
        foodToUpdate.updateFrom(sourceFoodToUpdate);

        //then
        assertEquals(sourceFoodToUpdate.getPortion(), foodToUpdate.getPortion());

    }
}