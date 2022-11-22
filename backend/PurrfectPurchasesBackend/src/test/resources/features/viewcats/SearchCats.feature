Feature: Search Cats

  Background: User is on Admin Page
    Given User is on home page
    Given User clicks on Admin link

  Scenario Outline: Search Cats
    When User clicks Search Cat button
    When User inputs <catid> into search field
    When User clicks Search button
    Then User should see <catid> information in table or be alerted

    Examples:
    | catid       |
    |1            |
    |2            |
    |3            |
    |7            |
    |15           |
    |0            |


