package io.github.JRojowski.controller;

import io.github.JRojowski.model.Food;
import io.github.JRojowski.model.Food;
import io.github.JRojowski.model.FoodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FoodControllerE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    FoodRepository foodRepo;

    @Test
    void httpGet_returnsAllTasks() {

        //given
        foodRepo.save(new Food("Pizza z kurczakiem", 400));
        foodRepo.save(new Food("Sorbet z Mango", 340));

        //when
        Food[] result = restTemplate.getForObject("http://localhost:" + port + "/foods", Food[].class);

        //then
        assertThat(result).hasSize(2);
    }
}