package io.github.JRojowski.model.projection;

import io.github.JRojowski.model.Food;

public class MealFoodReadModel {
    private String name;
    private Double price;
    private Integer portion;
    private Integer kcal;
    private Double protein;
    private Double fat;
    private Double carbs;
    private boolean reported;

    public MealFoodReadModel(Food source) {
        name = source.getName();
        price = source.getPrice();
        portion = source.getPortion();
        kcal = source.getKcal();
        protein = source.getProtein();
        fat = source.getFat();
        carbs = source.getCarbs();
        reported = source.isReported();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public Integer getPortion() {
        return portion;
    }

    public void setPortion(final Integer portion) {
        this.portion = portion;
    }

    public Integer getKcal() {
        return kcal;
    }

    public void setKcal(final Integer kcal) {
        this.kcal = kcal;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(final Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(final Double fat) {
        this.fat = fat;
    }

    public Double getCarbs() {
        return carbs;
    }

    public void setCarbs(final Double carbs) {
        this.carbs = carbs;
    }

    public boolean isReported() {
        return reported;
    }

    public void setReported(final boolean reported) {
        this.reported = reported;
    }
}
