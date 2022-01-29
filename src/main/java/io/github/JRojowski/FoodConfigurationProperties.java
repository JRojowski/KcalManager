package io.github.JRojowski;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("food")
public class FoodConfigurationProperties {
    private String testProp;

    public String getTestProp() {
        return testProp;
    }

    public void setTestProp(final String testProp) {
        this.testProp = testProp;
    }
}
