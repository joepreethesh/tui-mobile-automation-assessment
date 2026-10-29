Feature: Home screen functionality

  @smoke @home
  Scenario: Verify content is displayed under all Home screen tabs
    Given the app is launched
    And user is in "Home" screen
      | valid username |
      | valid password |
      | valid date     |
    And user verifies content is seen under the following tabs
      | All      |
      | Hotels   |
      | Holidays |

  @home
  Scenario: Verify app remains on Home screen after suspend and resume
    Given user is in "Home" screen
      | valid username |
      | valid password |
      | valid date     |
    When user sends the application to background for 5 seconds
    Then user sees Home screen