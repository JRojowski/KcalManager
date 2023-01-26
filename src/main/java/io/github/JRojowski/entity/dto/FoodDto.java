package io.github.JRojowski.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class FoodDto {
    @NotBlank(message = "Name must not be empty")
    private String name;
    private String producer;
    private Double price;
    private Integer portion;
    private Integer kcal;
    private Double protein;
    private Double fat;
    private Double carbs;
}
