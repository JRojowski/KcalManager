package io.github.JRojowski.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Builder
public class FoodDto {
    private int id;
    @NotBlank(message = "Name must not be empty")
    private String name;
    private String producer;
    private Double price;
    private Integer portion;
    private Integer kcal;
    private Double protein;
    private Double fat;
    private Double carbs;
    private List<String> mealList;
    private String environment;
}
