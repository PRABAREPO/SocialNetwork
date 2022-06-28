package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"./src/test/resources/Features/AddPosts.feature"},
		glue = {"stepdefinitions"},
		tags="@RegressionTest"
		)

public class AddPostsRunner {

}
