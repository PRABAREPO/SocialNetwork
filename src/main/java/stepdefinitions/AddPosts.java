package stepdefinitions;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepimplementations.AddPostsImplementation;

public class AddPosts {

	AddPostsImplementation stepImplement = new AddPostsImplementation();

	@Given("User takes the request body for adding posts")
	public void user_takes_the_request_body_for_adding_posts() throws IOException, ParseException {

		stepImplement.takeRequestBody();
	}

	@When("User sends the request with valid BasePath to Server for adding posts")
	public void user_sends_the_request_with_valid_BasePath_to_server_for_adding_posts() throws IOException {

		stepImplement.sendValidRequestToServer();
	}

	@Then("User receives and validates the Success response status code for adding posts")
	public void user_receives_and_validates_the_Success_response_status_code_for_adding_posts() {

		stepImplement.verifySuccessResponseStatusCode();
	}

	@And("Validates the response body from the server for adding posts")
	public void validates_the_response_body_from_the_server_for_adding_posts() throws ParseException {

		stepImplement.verifyResponseBody();
	}

	@When("User sends the request with invalid BasePath to Server for adding posts")
	public void user_sends_the_request_with_invalid_Basepath_to_server_for_adding_posts() throws IOException {

		stepImplement.sendInvalidRequestToServer();

	}

	@Then("User receives and validates the Error response status code for adding posts")
	public void user_receives_and_validates_the_Error_response_status_code_for_adding_posts() {

		stepImplement.verifyErrorResponseStatusCode();
	}

	@And("Validates the Empty response body from the server for adding posts")
	public void validates_the_Empty_response_body_from_the_server_for_adding_posts() throws ParseException {

		stepImplement.verifyEmptyResponseBody();
	}

}
