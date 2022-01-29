package io.github.JRojowski.model;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeId;
    private float grams;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;


    public Recipe() {
    }


    public Integer getRecipeId() {
        return recipeId;
    }

    void setRecipeId(final Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Food getFood() {
        return food;
    }

    void setFood(final Food food) {
        this.food = food;
    }

    public Meal getMeal() {
        return meal;
    }

    void setMeal(final Meal meal) {
        this.meal = meal;
    }

    public float getGrams() {
        return grams;
    }

    void setGrams(final float grams) {
        this.grams = grams;
    }


}
