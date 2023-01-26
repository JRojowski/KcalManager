package io.github.JRojowski.controller;

import io.github.JRojowski.service.MealService;
import io.github.JRojowski.repository.MealRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
class MealController {

    public static final Logger logger = LoggerFactory.getLogger(MealController.class);

    MealController(final MealRepository repository, final MealService service) {
    }

}
