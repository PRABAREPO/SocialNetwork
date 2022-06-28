package stepdefinitions;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepimplementations.AddCommentsImplementation;

public class AddComments {

	AddCommentsImplementation stepImplement = new AddCommentsImplementation();

	@Given("User takes the request body for adding comments")
	public void user_takes_the_request_body_for_adding_comments() throws IOException, ParseException {

		stepImplement.takeRequestBody();
	}

	@When("^User sends the request with \"([^\"]*)\" to Server for adding comments$")
	public void user_sends_the_valid_request_with_valid_basepath_to_server_for_adding_comments(String validBasePath)
			throws IOException {

		stepImplement.sendValidRequestToServer(validBasePath);
	}

	@Then("User receives and validates the Success response status code for adding comments")
	public void user_receives_and_validates_the_Success_response_status_code_for_adding_comments() {

		stepImplement.verifySuccessResponseStatusCode();
	}

	@And("Validates the response body from the server for adding comments")
	public void validates_the_response_body_from_the_server_for_adding_comments() throws ParseException {

		stepImplement.verifyResponseBody();
	}

	@When("^User sends the request with \"([^\"]*)\" to Server for adding comment$")
	public void user_sends_the_request_with_invalid_basepath_to_server_for_adding_comments(String invalidBasePath)
			throws IOException {

		stepImplement.sendInvalidRequestToServer(invalidBasePath);

	}

	@Then("User receives and validates the Error response status code for adding comments")
	public void user_receives_and_validates_the_Error_response_status_code_for_adding_comments() {

		stepImplement.verifyErrorResponseStatusCode();
	}

	@And("Validates the Empty response body from the server for adding comments")
	public void validates_the_Empty_response_body_from_the_server_for_adding_comments() throws ParseException {

		stepImplement.verifyEmptyResponseBody();
	}

}
