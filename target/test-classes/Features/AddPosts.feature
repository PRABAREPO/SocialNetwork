Feature: To test the functionality of adding posts
  
  @SmokeTest @RegressionTest
  Scenario: User tries to add posts with valid input
    Given User takes the request body for adding posts
    When User sends the request with valid BasePath to Server for adding posts
    Then User receives and validates the Success response status code for adding posts
    And Validates the response body from the server for adding posts
    
  @RegressionTest
  Scenario: User tries to add posts with Invalid input
    Given User takes the request body for adding posts
    When User sends the request with invalid BasePath to Server for adding posts
    Then User receives and validates the Error response status code for adding posts
    And Validates the Empty response body from the server for adding posts
    
    

 
