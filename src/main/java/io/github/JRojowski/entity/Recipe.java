package io.github.JRojowski.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.JRojowski.util.RecipePK;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @EmbeddedId
    private RecipePK recipeId = new RecipePK();
    @ManyToOne
    @MapsId("mealId")
    @JoinColumn(name = "meal_id")
    @JsonIgnore
    private Meal meal;
    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(name = "food_id")
    private Food food;
    private float grams;


}
