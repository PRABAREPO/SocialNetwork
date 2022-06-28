package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"./src/test/resources/Features/FetchUserList.feature"},
		glue = {"stepdefinitions"},
		tags="@RegressionTest"
		)

public class FetchUserListRunner {

}
