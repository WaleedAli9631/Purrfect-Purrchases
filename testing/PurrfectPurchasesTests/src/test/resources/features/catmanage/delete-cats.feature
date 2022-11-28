Feature: Delete Cats

  Background: User is on Admin Page
    Given User is on home page
    Given User clicks Login Link
    Given User signs in as Admin
    Given User clicks on Admin link

  Scenario: Delete Cats
    When User clicks delete button for cat
    Then User should see alert delete confirmation
    Then User should NOT see deleted cat in table