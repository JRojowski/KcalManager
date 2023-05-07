package io.github.JRojowski.entity.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealDto {

    private Integer id;
    @NotBlank(message = "Name must not be empty")
    private String name;
}
