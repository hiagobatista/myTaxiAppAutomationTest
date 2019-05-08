Feature: Authentication

  @login-feature
  Scenario: Successful login
    Given I am on login screen
    When I enter email crazydog335
    And I enter password venture
    And I click on login button
    Then I should not see error message


  @login-feature
  Scenario: Failed login
    Given I am on login screen
    When I enter email test
    And I enter password test1234
    And I click on login button
    Then I should see error message