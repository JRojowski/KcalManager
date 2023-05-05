package io.github.JRojowski.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class RecipePK implements Serializable {
    @Column(name = "meal_id")
    private Integer mealId;
    @Column(name = "food_id")
    private Integer foodId;
}
