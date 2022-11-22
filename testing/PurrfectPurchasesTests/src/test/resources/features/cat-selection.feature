Feature: Cat Selection

  Scenario: Cat not added twice
    Given user is the Home page
    When user selects a cat
    When user tries to add the same cat again to their cart
    Then user should see an alert saying they cannot add it to their cart again

  Scenario: Only two cats per user in cart
    Given user is on the Home page
    When they adds two cats to their cart