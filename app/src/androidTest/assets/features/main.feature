Feature: Main

  @main-feature
  Scenario: Verify user is able to search for a driver
    Given I am on main screen
    When I enter sa on search box
    Then I should see drivers on autocomplete list