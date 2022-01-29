package io.github.JRojowski.model.projection;

import io.github.JRojowski.model.Food;
import io.github.JRojowski.model.Meal;
import io.github.JRojowski.model.Recipe;

import java.util.Set;
import java.util.stream.Collectors;

public class MealReadModel {

    private String name;
    /**
     * Boolean reported from sum of reported foods
     */
    private boolean reported;
    private Set<MealFoodReadModel> ingredients;

    public MealReadModel(Meal source) {
        name = source.getName();
        reported = source.getIngredients().stream()
                         .map(Food::isReported)
                         .collect(Collectors.toList()).contains(true);
        ingredients = source.getIngredients().stream()
                            .map(MealFoodReadModel::new)
                            .collect(Collectors.toSet());
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isReported() {
        return reported;
    }

    public void setReported(final boolean reported) {
        this.reported = reported;
    }

    public Set<MealFoodReadModel> getIngredients() {
        return ingredients;
    }

    public void setIngredients(final Set<MealFoodReadModel> ingredients) {
        this.ingredients = ingredients;
    }
}
