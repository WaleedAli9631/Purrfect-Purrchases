Feature: Adoption page

  Background: On Adoption page
    Given customer is on Adoption page

  Scenario: Customer logs in
    Given customer was not logged in
    When customer logs in
    Then login and register buttons disappear from page
    Then logout button becomes visible
    Then shipping information appears

  Scenario: Customer logs out
    Given customer is logged in
    When customer clicks log out
    Then log in and register buttons become visible
    Then cart area should be empty
    Then shipping information disappears

  Scenario: No cat in cart
    When there is no cat in the cart on the Adoption page
    When the user clicks on the PURCHASE NOW button
    Then user should get an alert that there is no cat in their cart








