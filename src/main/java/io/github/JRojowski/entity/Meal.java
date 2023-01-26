package io.github.JRojowski.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mealId;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private Set<Recipe> recipes = new HashSet<>();

}
