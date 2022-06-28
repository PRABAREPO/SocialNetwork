Feature: To test the functionality of adding comments

  @SmokeTest @RegressionTest
  Scenario Outline: User tries to add comments with valid input
    Given User takes the request body for adding comments
    When User sends the request with "<ValidBasePath>" to Server for adding comments
    Then User receives and validates the Success response status code for adding comments
    And Validates the response body from the server for adding comments
    
    Examples:
    |ValidBasePath|
    |/comments|
    
    
  @RegressionTest
  Scenario Outline: Users tries to add comments with Invalid input
    Given User takes the request body for adding comments
    When User sends the request with "<InvalidBasePath>" to Server for adding comment
    Then User receives and validates the Error response status code for adding comments
    And Validates the Empty response body from the server for adding comments
    
    Examples:
    |InvalidBasePath|
    |/comment|
    
    