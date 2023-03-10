package io.github.JRojowski.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Food extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name must not be empty")
    private String name;
    private String producer;
    private Integer portion;
    private Double price;
    private Integer kcal;
    private Double protein;
    private Double fat;
    private Double carbs;
    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private Set<Recipe> recipes;
    private Boolean reported;
}
