package io.github.JRojowski.logic;

import io.github.JRojowski.model.Food;
import io.github.JRojowski.model.Meal;
import io.github.JRojowski.model.MealRepository;
import io.github.JRojowski.model.Recipe;
import io.github.JRojowski.model.projection.MealFoodWriteModel;
import io.github.JRojowski.model.projection.MealReadModel;
import io.github.JRojowski.model.projection.MealWriteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService {

    private MealRepository repository;

    MealService(final MealRepository repository) {
        this.repository = repository;
    }

    public MealReadModel createMeal(MealWriteModel source) {
        var result = new Meal();
        result.setName(source.getName());
        result.setIngredients(source.getIngredients().stream()
                                    .map(MealFoodWriteModel::toFood)
                                    .collect(Collectors.toSet()));
        repository.save(result);
        return new MealReadModel(result);
    }

    public List<MealReadModel> readAll() {
        return repository.findAll().stream()
                         .map(MealReadModel::new)
                         .collect(Collectors.toList());
    }


}
