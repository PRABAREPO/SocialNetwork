Feature: To test the functionality of fetching user list
  
  @SmokeTest @RegressionTest
  Scenario: Admin user tries to fetch list of users with valid input
    Given Admin user is ready to fetch the list of user details
    When Admin user sends the request with valid BasePath to Server for fetching user list
    Then Admin user receives and validates the Success response status code for fetching user list
    And Validates the response body from the server for fetching user list
    
  @RegressionTest
  Scenario: Admin user tries to fetch list of users with Invalid input
    Given Admin user is ready to fetch the list of user details
    When Admin user sends the request with invalid BasePath to Server for fetching user list
    Then Admin user receives and validates the Error response status code for fetching user list
    And Validates the Empty response body from the server for fetching user list
    
    

 
