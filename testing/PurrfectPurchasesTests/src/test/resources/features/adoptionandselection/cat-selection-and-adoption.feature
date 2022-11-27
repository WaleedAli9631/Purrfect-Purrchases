Feature: Cat Selection

  Scenario: Selected cat on Adoption page
    Given customer selects cat and navigates to the Adoption page
    Then customer sees cat they selected on the Adoption page

  Scenario: Customer deletes selected cat
    Given customer selects cat and navigates to the Adoption page
    Then customer sees REMOVE button and clicks it
    Then the cat should disappear from the Adoption page and cart total should update

  Scenario: Unidentified user tries to adopt cat
    Given customer is not logged in
    Given customer selects cat and navigates to the Adoption page
    When customer sees PURCHASE NOW button and clicks it
    Then an alert should appear letting the user know they must log in or register first

  @Test4
  Scenario: Cat selection followed by sign up
    Given customer is not logged in
    Given customer selects cat and navigates to the Adoption page
    When customer signs up as a new user
    Then login and register buttons disappear from page
    Then logout button becomes visible
    Then shipping information appears
    Then cat they selected is visible in the cart

  @Test5
  Scenario: Customer adopts cat
    Given customer selects cat and navigates to the Adoption page
    When customer logs in
    When customer sees PURCHASE NOW button and clicks it
    When customer gets confirmation alert and confirms they want to buy the cat(s)
    Then customer gets order complete confirmation
    Then the cat should disappear from the Adoption page and cart total should update
    Then adopted cat is not viewable on Home page

  @Test6
  Scenario: Adopted cat disappears from cart
    Given customer1 adds cat to cart
    When customer2 adopts cat that customer1 added
    When customer1 logs back in and is on adoption
    Then customer1 should receive an alert the cat was removed from their cart
