package io.github.JRojowski.model.projection;

import io.github.JRojowski.model.Meal;
import io.github.JRojowski.model.Recipe;

import java.util.Set;

public class MealDTO {

    public String name;

    public Set<Recipe> ingredients;

    public MealDTO() {
    }

}