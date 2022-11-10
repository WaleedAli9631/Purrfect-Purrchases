Feature: Put Cat In Cart

  Background: User is on Homepage
    Given User is on "Cat Description" page

  Scenario: Click Adopt Cats
    When User clicks on adopt button
    Then User should SEE LOGIN PAGE OR CART PAGE :-/

