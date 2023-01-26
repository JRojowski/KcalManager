package io.github.JRojowski.entity.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class CaloriesDto {

    private int kcal;
    private double protein;
    private double fat;
    private double carbs;
}
