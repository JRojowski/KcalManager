package io.github.JRojowski.entity.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class FoodCaloriesDto extends CaloriesDto {

    private int portion;

}
