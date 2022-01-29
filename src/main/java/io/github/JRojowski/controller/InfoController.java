package io.github.JRojowski.controller;

import io.github.JRojowski.FoodConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class InfoController {

    private final DataSourceProperties dataSource;
    private final FoodConfigurationProperties myProp;

    InfoController(final DataSourceProperties dataSource, final FoodConfigurationProperties myProp) {
        this.dataSource = dataSource;
        this.myProp = myProp;
    }

    @GetMapping("/info/url")
    String url() {
        return dataSource.getUrl();
    }

    @GetMapping("info/prop")
    String myProp() {
        return myProp.getTestProp();
    }
}
