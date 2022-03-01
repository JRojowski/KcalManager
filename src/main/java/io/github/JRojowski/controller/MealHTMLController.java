package io.github.JRojowski.controller;

import io.github.JRojowski.logic.FoodService;
import io.github.JRojowski.logic.MealService;
import io.github.JRojowski.model.Food;
import io.github.JRojowski.model.Meal;
import io.github.JRojowski.model.Recipe;
import io.github.JRojowski.model.projection.MealWriteModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mealsHTML")
class MealHTMLController {

    private final MealService service;

    MealHTMLController(final MealService service) {
        this.service = service;
    }

    @GetMapping
    String showMeals(Model model) {
        model.addAttribute("meal", new MealWriteModel());
        return "meals";
    }

    @PostMapping
    String addMeal(
            @ModelAttribute("meal") @Valid MealWriteModel current,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            return "meals";
        }
        service.createMeal(current);
        model.addAttribute("meal", new MealWriteModel());
        model.addAttribute("meals", getMeals());
        model.addAttribute("message", "Dodano posiłek!");
        return "meals";
    }

    @PostMapping(params = "addIngredient")
    String addNextIngredient(@ModelAttribute("meal") MealWriteModel current) {
        current.getIngredients().add(new Recipe());
        return "meals";
    }

    @ModelAttribute("meals")
    List<Meal> getMeals() {
        return service.readAll();
    }


    //List<Food> foods =  foodService.readAllFood();
    //model.addAttribute("operators", operators);
}
