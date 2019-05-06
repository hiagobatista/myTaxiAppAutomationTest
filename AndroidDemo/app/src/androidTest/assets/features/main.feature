Feature: Main

  @main-feature
  Scenario: Verify user is able to search for a driver
    Given I am on main screen
    When I enter sa on search box
    Then I should see drivers on autocomplete list

  @main-feature
  Scenario: Verify dialer screen
    Given I am on main screen
    When I enter sa on search box
    And I select Sarah Scott from autocomplete list
    Then I should see Sarah Scott profile
    When I click on call button
    Then Phone dialer screen should be open displaying driver's phone 413-868-2228