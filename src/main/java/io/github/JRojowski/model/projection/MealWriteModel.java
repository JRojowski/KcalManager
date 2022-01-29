package io.github.JRojowski.model.projection;

import io.github.JRojowski.model.Meal;

import java.util.Set;
import java.util.stream.Collectors;


public class MealWriteModel {

    private String name;
    private Set<MealFoodWriteModel> ingredients;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<MealFoodWriteModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(final Set<MealFoodWriteModel> ingredients) {
        this.ingredients = ingredients;
    }

    public Meal toMeal() {
       var result = new Meal();
       result.setName(name);
       result.setIngredients(
               ingredients.stream()
                          .map(MealFoodWriteModel::toFood)
                          .collect(Collectors.toSet())
       );
       return result;
   }
}
