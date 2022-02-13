package io.github.JRojowski.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {

    @EmbeddedId
    private RecipePK recipeId = new RecipePK();

    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @MapsId("mealId")
    @JoinColumn(name = "meal_id")
    @JsonIgnore
    private Meal meal;

    private float grams;


    public Recipe() {
    }


    public Food getFood() {
        return food;
    }

    public void setFood(final Food food) {
        this.food = food;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(final Meal meal) {
        this.meal = meal;
    }

    public float getGrams() {
        return grams;
    }

    public void setGrams(final float grams) {
        this.grams = grams;
    }

}
