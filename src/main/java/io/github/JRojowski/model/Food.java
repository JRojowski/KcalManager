package io.github.JRojowski.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name must not be empty")
    private String name;
    private String producer;
    private Double price;

    private Integer kcal;
    private Double protein;
    private Double fat;
    private Double carbs;

    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(final String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    private void setProducer(final String producer) {
        this.producer = producer;
    }

    public Double getPrice() {
        return price;
    }

    private void setPrice(final Double price) {
        this.price = price;
    }

    public Integer getKcal() {
        return kcal;
    }

    private void setKcal(final Integer kcal) {
        this.kcal = kcal;
    }

    public Double getProtein() {
        return protein;
    }

    private void setProtein(final Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    private void setFat(final Double fat) {
        this.fat = fat;
    }

    public Double getCarbs() {
        return carbs;
    }

    private void setCarbs(final Double carbs) {
        this.carbs = carbs;
    }
}
