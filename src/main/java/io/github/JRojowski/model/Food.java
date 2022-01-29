package io.github.JRojowski.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodId;

    @NotBlank(message = "Name must not be empty")
    private String name;
    private String producer;
    private Double price;
    private Integer portion;

    private Integer kcal;
    private Double protein;
    private Double fat;
    private Double carbs;

    @OneToMany(mappedBy = "food")
    private Set<Recipe> recipes = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Recipes",
            joinColumns = {@JoinColumn(name = "food_id")},
            inverseJoinColumns = {@JoinColumn(name = "meal_id")}
    )
    private Set<Meal> meals = new HashSet<>();

    @Embedded
    private Audit audit = new Audit();
    private boolean reported;


    public Food() {
    }

    public Food(String name, Integer portion) {
       this.name = name;
       this.portion = portion;
    }

    public int getFoodId() {
        return foodId;
    }

    void setFoodId(final int id) {
        this.foodId = id;
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

    public Integer getPortion() {
        return portion;
    }

    void setPortion(final Integer portion) {
        this.portion = portion;
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

    Set<Recipe> getRecipes() {
        return recipes;
    }

    void setRecipes(final Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public boolean isReported() {
        return reported;
    }

    public void setReported(final boolean reported) {
        this.reported = reported;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(final Set<Meal> meals) {
        this.meals = meals;
    }

    public void updateFrom(final Food source) {
        name = source.name;
        producer = source.producer;
        price = source.price;
        portion = source.portion;
        kcal = source.kcal;
        protein = source.protein;
        fat = source.fat;
        carbs = source.carbs;
        recipes = source.recipes;
    }

}
