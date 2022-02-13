package io.github.JRojowski.model;

import io.github.JRojowski.model.projection.MealDTO;

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

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private Set<Recipe> recipes = new HashSet<>();


    public Meal() {
    }

    public Meal(final String name) {
        this.name = name;
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


}
