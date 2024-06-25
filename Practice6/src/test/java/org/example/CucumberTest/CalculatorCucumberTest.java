package org.example.CucumberTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "stepDefinitions",
        plugin = {"pretty",
                "html:target/cucumberReports/mobile/report.html",
                "json:target/cucumberReports/mobile/report.json"
        })

public class CalculatorCucumberTest {
}

