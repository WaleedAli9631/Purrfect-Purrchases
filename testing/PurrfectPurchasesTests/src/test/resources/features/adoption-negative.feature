Feature: Adoption Negative

  Background: Customer is on the Adoption page
    Given customer is on Adoption page

  Scenario: Unidentified user tries to adopt cat
    Given customer is not logged in
    When user sees PURCHASE NOW button and clicks it
    Then an alert should appear letting the user know they must log in or register first

  Scenario: No cat in cart
    When there is no cat in the cart on the Adoption page
    When the user clicks on the PURCHASE NOW button
    Then user should get an alert that there is no cat in their cart

