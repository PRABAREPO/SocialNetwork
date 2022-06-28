package stepdefinitions;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepimplementations.FetchUserListImplementation;

public class FetchUserList {
	
	FetchUserListImplementation stepImplement = new FetchUserListImplementation();

	@Given("Admin user is ready to fetch the list of user details")
	public void admin_user_is_ready_to_fetch_the_list_of_user_details() {
	  
		//Admin User has access to fetch the list of users
	}
	
	@When("Admin user sends the request with valid BasePath to Server for fetching user list")
	public void admin_user_sends_the_request_with_valid_base_path_to_server_for_fetching_user_list() throws IOException {
		
		stepImplement.sendValidRequestToServer();
	}
	
	@Then("Admin user receives and validates the Success response status code for fetching user list")
	public void admin_user_receives_and_validates_the_success_response_status_code_for_fetching_user_list() {
		
		stepImplement.verifySuccessResponseStatusCode();
	}
	
	@And("Validates the response body from the server for fetching user list")
	public void validates_the_response_body_from_the_server_for_fetching_user_list() throws ParseException {
	   
		stepImplement.verifyResponseBody();
	}

	@When("Admin user sends the request with invalid BasePath to Server for fetching user list")
	public void admin_user_sends_the_request_with_invalid_base_path_to_server_for_fetching_user_list() throws IOException {

		stepImplement.sendInvalidRequestToServer();
	}
	
	@Then("Admin user receives and validates the Error response status code for fetching user list")
	public void admin_user_receives_and_validates_the_error_response_status_code_for_fetching_user_list() {

		stepImplement.verifyErrorResponseStatusCode();
	}
	
	@Then("Validates the Empty response body from the server for fetching user list")
	public void validates_the_empty_response_body_from_the_server_for_fetching_user_list() throws ParseException {
		
		stepImplement.verifyEmptyResponseBody();
	}






}
