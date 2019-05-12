Feature: Driver Profile

  @profile-feature
  Scenario: Verify phone dialer screen displays driver's phone number
    Given I navigate to driver profile
    When I click on call button
    Then Phone dialer screen should be open displaying driver's phone 413-868-2228