Feature: Cat Selection

  Scenario: Cat not added twice
    Given user is the Home page
    When user selects a cat
    When user tries to add the same cat again to their cart
    Then user should see an alert saying they cannot add it to their cart again

  Scenario: Only two cats per user in cart
    Given user is on the Home page
    When user add two cats to their cart
    When user tries to add a third cat to their cart
    Then user gets an alert that they cannot add another cat

  Scenario: Adopted cat disappears from cart
    Given user1 adds cat to cart
    When user2 adopts cat that user1 added
    When user1 logs back in and is on adoption
    Then user1 should recieve an alert the cat was removed from their cart