Feature: Login functionality

  @smoke @login
  Scenario: Verify user is able to login to the application
    Given the app is launched
    And user is in "Login" screen
      | valid username |
      | valid password |
      | valid date     |
    When user clicks SignIn button
    Then user sees Products screen