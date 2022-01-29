package io.github.JRojowski.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mealId;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @OneToMany(mappedBy = "meal")
    private Set<Recipe> recipes = new HashSet<>();

    @ManyToMany(mappedBy = "meals")
    private Set<Food> ingredients = new HashSet<>();


    public Meal() {
    }


    public int getMealId() {
        return mealId;
    }

    void setMealId(final int mealId) {
        this.mealId = mealId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(final Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Set<Food> getIngredients() {
        return ingredients;
    }

    public void setIngredients(final Set<Food> ingredients) {
        this.ingredients = ingredients;
    }

}
