package io.github.JRojowski.config;

import io.github.JRojowski.entity.Food;
import io.github.JRojowski.entity.Recipe;
import io.github.JRojowski.entity.dto.FoodDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Food.class, FoodDto.class).addMappings(
                mapper -> mapper.using(recipeToMealConverter).map(Food::getRecipes, FoodDto::setMealList)
        );

        return modelMapper;
    }

    Converter<Collection<Recipe>, List<String>> recipeToMealConverter = context -> context.getSource()
            .stream()
            .map(recipe -> recipe.getMeal().getName())
            .collect(Collectors.toList());
}
