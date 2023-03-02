package io.github.JRojowski.entity.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Builder
public class MealDto {

    @NotBlank
    private String name;
    private List<RecipeDto> recipeDtoList;
}
