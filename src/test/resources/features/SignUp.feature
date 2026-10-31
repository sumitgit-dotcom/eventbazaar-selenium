Feature: Vendor Sign Up

  @signup
  Scenario: Register as a vendor with company and category details
    Given I am on the EventBazaar homepage
    When I click on the Login button
    And I click on the Sign Up link
    And I enter company name "URANUS"
    And I select business category "Event Venue"
    And I select business sub category "Party Plot"
    And I click on the Next button
    Then I should be navigated to the next step of sign up