package io.github.JRojowski.config;

import lombok.SneakyThrows;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class ModelMapperTestConfig {

    @SneakyThrows
    public ModelMapperTestConfig() {
        for (Field field : this.getClass().getDeclaredFields()) {
            FieldUtils.writeField(this, field.getName(), mock(field.getType()), true);
        }
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapperConfig().modelMapper();
    }
}
