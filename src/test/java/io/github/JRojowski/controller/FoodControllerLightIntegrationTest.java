package io.github.JRojowski.controller;

import io.github.JRojowski.model.Food;
import io.github.JRojowski.model.FoodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FoodController.class)
@ActiveProfiles("integration")
class FoodControllerLightIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FoodRepository repo;

    @Test
    void httpGet_returnsGivenFood() throws Exception {

        // given
        when(repo.findById(anyInt()))
                .thenReturn(Optional.of(new Food("Pizza z kurczakiem", 400)));

        // when
        // then
        mockMvc.perform(get("/foods/2137"))
                .andDo(print())
                .andExpect(content().string(containsString("\"name\":\"Pizza z kurczakiem\"")));

    }


}

