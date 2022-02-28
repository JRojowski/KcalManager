package io.github.JRojowski.model.projection;

import io.github.JRojowski.model.Meal;
import io.github.JRojowski.model.Recipe;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MealWriteModel {

    @NotBlank(message = "Name must not be empty")
    private String name;

    @Valid
    private List<Recipe> ingredients = new ArrayList<>();

    public MealWriteModel() {
        ingredients.add(new Recipe());
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Recipe> getIngredients() {
        return ingredients;
    }

    public void setIngredients(final List<Recipe> ingredients) {
        this.ingredients = ingredients;
    }

}
