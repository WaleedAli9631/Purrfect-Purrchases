@register
Feature: Registration Positive

  Background: User is on home page
    Given User is on home page

  Scenario Outline: User Registers with correct info
    When User clicks the register link
    When User inputs a random email into the registration email field
    When User inputs <password> into the registration password field
    When User inputs <password> into the registration password confirmation field
    When User inputs <fname> into the registration first name field
    When User inputs <lname> into the registration password last name field
    When User inputs <streetAddress> into the registration street address field
    When User inputs <city> into the registration city field
    When User inputs <state> into the registration state field
    When User clicks the register button
    Then User should see the account page link
    Then User should see the logout button


    Examples:
      | password | fname | lname | streetAddress | city | state |
     |"1234567" | "FakeName" | "FakeName"|"fake street"| "fake city"|"fake state" |
     |"1234568" | "FakeName" | "FakeName"|"fake street"| "fake city"|"fake state" |

