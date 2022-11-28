Feature: Search Cats

  Background: User is on Admin Page
    Given User is on home page
    Given User clicks Login Link
    Given User signs in as Admin
    Given User clicks on Admin link

  Scenario Outline: Search Cats
    When User clicks Search Cat button
    When User inputs <catid> into search field
    When User takes a photo and calls it "searchcat-before.png"
    When User clicks Search button
    Then User should see <catid> information in table or be alerted
    When User takes a photo and calls it "searchcat-after.png"

    Examples:
    | catid       |
    |1            |
    |2            |
    |3            |
    |7            |
    |15           |
    |0            |


