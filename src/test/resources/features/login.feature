Feature: Login functionality

  @smoke @login
  Scenario: Verify Home screen is displayed on logging in with valid credentials
    Given the app is launched
    And user is in "Login" screen
      | valid username |
      | valid password |
      | valid date     |
    When user clicks Submit button
    Then user sees Home screen

  @smoke @login
  Scenario: Verify validation error when submitting empty login fields
    Given the app is launched
    When user clicks Submit button
    Then user sees error messages are displayed

  @ui @login
  Scenario: Verify the UI elements in the Login screen
    Given the app is launched
    Then user verifies the following UI elements are displayed
      | Username text field      |
      | Password text field      |
      | Date of birth text field |
      | Eye button               |
      | Calendar icon            |
      | Submit button            |