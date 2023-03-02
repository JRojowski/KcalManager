package io.github.JRojowski.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class CaloriesDto {

    private String name;
    private double kcal;
    private double protein;
    private double fat;
    private double carbs;
}
