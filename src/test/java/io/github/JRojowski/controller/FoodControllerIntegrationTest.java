package io.github.JRojowski.controller;

import io.github.JRojowski.model.Food;
import io.github.JRojowski.model.FoodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
class FoodControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FoodRepository repo;

    @Test
    void httpGet_returnsGivenFood() throws Exception {

        // given
        int id = repo.save(new Food("Pizza z kurczakiem", 400)).getFoodId();

        // when
        // then
        mockMvc.perform(get("/foods/" + id))
                .andExpect(status().is2xxSuccessful());

    }


}

