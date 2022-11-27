Feature: Cat selection on Home page

  Background: Customer is on Home page
    Given customer is on Home page

  Scenario: Navigation
    Then customer navigates to the Adoption page
    Then customer navigates back to the Home page

  Scenario: Cat selected confirmation
    When customer selects a cat
    Then customer will get a confirmation that the cat has been added to their cart

  Scenario: Cat not added twice
    When customer selects a cat
    When customer tries to add the same cat again to their cart
    Then customer should see an alert saying they cannot add it to their cart again

  Scenario: Only two cats per user in cart
    When customer add two cats to their cart
    When customer tries to add a third cat to their cart
    Then customer gets an alert that they cannot add another cat