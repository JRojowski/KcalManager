package io.github.JRojowski.util;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RecipePK implements Serializable {

    private int mealId;
    private int foodId;

    public int getMealId() {
        return mealId;
    }

    public void setMealId(final int mealId) {
        this.mealId = mealId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(final int foodId) {
        this.foodId = foodId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RecipePK recipePK = (RecipePK) o;
        return Objects.equals(mealId, recipePK.mealId) && Objects.equals(foodId, recipePK.foodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealId, foodId);
    }
}
