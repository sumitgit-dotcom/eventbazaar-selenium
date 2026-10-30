
Feature: EventBazaar Homepage

  Scenario: Verify EventBazaar homepage loads successfully

    Given I launch the EventBazaar homepage
    When the homepage is loaded
    Then the EventBazaar homepage should be displayed
