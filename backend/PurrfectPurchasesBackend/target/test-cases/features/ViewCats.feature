Feature: View Cats

  Background: User is on Homepage
    Given User is on "Homepage" page

  Scenario: View Cats
    When User clicks on cat image
    Then User is on cat description page
    Then Cat description page information matches cat thumbnail information

    Scenario: Do not view cats that have been sold
      When User reviews all cats on page
      Then User finds no cats have been sold


