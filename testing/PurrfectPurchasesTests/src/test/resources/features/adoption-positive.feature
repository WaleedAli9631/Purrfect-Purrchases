Feature: Adoption Positive

  Background: Customer is on the Adoption page
    Given customer is on Adoption page

  Scenario: Selected cat on Adoption page
    Then customer sees cat they selected on the Adoption page
    Then customer will see Purchase Now button on Adoption page

  Scenario: Customer deletes selected cat
    Given customer sees cat they selected on the Adoption page
    Then customer sees REMOVE button
    When customer clicks the REMOVE button
    Then the cat should disappear from the Adoption page and cart total should update

  Scenario: Customer logs in
    Given customer was not logged in
    When customer logs in or registers
    Then login and register buttons disappear from page
    Then logout button becomes visible

  Scenario: Customer logs out
    Given user is logged in
    When user clicks log out
    Then log in and register buttons become visible
    Then cart area should be empty

  Scenario: Customer adopts cat
    When customer sees PURCHASE NOW button and clicks it
    When customer gets confirmation alert and confirms they want to buy the cat(s)
    Then customer gets order complete confirmation and the cat(s) disappears from the cart area
    Then cart total is updated to 0






