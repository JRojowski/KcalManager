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
public class Meal extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private Set<Recipe> recipes;

}
