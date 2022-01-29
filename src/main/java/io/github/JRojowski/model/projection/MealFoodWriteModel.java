package io.github.JRojowski.model.projection;

import io.github.JRojowski.model.Food;

public class MealFoodWriteModel {

    private String name;
    private Integer portion;


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getPortion() {
        return portion;
    }

    public void setPortion(final Integer portion) {
        this.portion = portion;
    }

    public Food toFood() {
        return new Food(name, portion);
    }
}
