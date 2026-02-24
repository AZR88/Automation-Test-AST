package v3.bdd.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/v3_bdd",
        glue = "v3.bdd.steps",
        plugin = {"pretty", "html:target/v3-report.html"}
)
public class TestRunners {
}