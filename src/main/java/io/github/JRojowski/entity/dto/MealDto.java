package io.github.JRojowski.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class MealDto {

    @NotBlank(message = "Name must not be empty")
    private String name;
}
