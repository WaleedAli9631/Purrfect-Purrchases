Feature: Delete Cats

  Background: User is on Admin Page
    Given User is on home page
    Given User clicks on Admin link

  Scenario: Delete Cats
    When User clicks delete button for cat
    Then User should see alert delete confirmation
    Then User should NOT see cat with <catID> in table