package org.desafio.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/org/desafio/features/",
        glue = {"org.desafio.steps", "org.desafio.config"},
        plugin = {"pretty", "html:target/cucumber-reports"},
        tags= "@All",
        monochrome = true
)
public class TestRunner {
}