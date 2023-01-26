package io.github.JRojowski.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class FoodCaloriesDto extends CaloriesDto {
    @JsonProperty(index = 1)
    private String name;
    @JsonProperty(index = 2)
    private int portion;

}
