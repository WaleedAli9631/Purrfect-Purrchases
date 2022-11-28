@register
Feature: Registration Negative

  Background: User is on home page
    Given User is on home page

  Scenario Outline: User Registers with invalid info
    When User clicks the register link
    When User inputs a random email into the registration email field
    When User inputs <password> into the registration password field
    When User inputs <password2> into the registration password confirmation field
    When User inputs <fname> into the registration first name field
    When User inputs <lname> into the registration password last name field
    When User inputs <streetAddress> into the registration street address field
    When User inputs <city> into the registration city field
    When User inputs <state> into the registration state field
    When User clicks the register button
    Then User should see an alert indicating something went wrong


    Examples:
      | password |password2 | fname | lname | streetAddress | city | state |
      |"whPKUyIMOUyhmoyBjQW112345" |"whPKUyIMOUyhmoyBjQW112345"| "FakeName" | "FakeName"|"fake street"| "fake city"|"fake state" |
      |"1234567" | "12" | "FakeName" | "FakeName"|"fake street"| "fake city"|"fake state" |
      |"12345(67" | "12345(67" | "FakeName" | "FakeName"|"fake street"| "fake city"|"fake state" |
      |"12345(67" | "12345(67" | "FakeName" | "FakeName"|"fake street"| "fake city"|"fake state" |
      |"12345(67" | "12345(67" | "FakeName" | "FakeName"|"fa"| "fake city"|"fake state" |
      |"12345(67" | "12345(67" | "FakeName" | "FakeName"|"fawhPKUyIMOUyhmoyBjQW112345"| "fake city"|"fake state" |
      |"12345(67" | "12345(67" | "FakeName" | "FakeName"|"fake street"| "ci"|"fake state" |
      |"12345(67" | "12345(67" | "FakeName" | "FakeName"|"fake street"| "ciwhPKUyIMOUyhmoyBjQW112345"|"fake state" |
      |"12345(67" | "12345(67" | "FakeName" | "FakeName"|"fake street"| "fake city"|"st" |
      |"12345(67" | "12345(67" | "FakeName" | "FakeName"|"fake street"| "fake city"|"stciwhPKUyIMOUyhmoyBjQW112345" |

  Scenario Outline: User Registers with invalid info and missing fields
    When User clicks the register link
    When User inputs <password> into the registration password field
    When User inputs <password2> into the registration password confirmation field
    When User inputs <fname> into the registration first name field
    When User inputs <lname> into the registration password last name field
    When User inputs <streetAddress> into the registration street address field
    When User inputs <city> into the registration city field
    When User inputs <state> into the registration state field
    When User clicks the register button
    Then User should see a tooltip saying to fill the required field


    Examples:
      | password |password2 | fname | lname | streetAddress | city | state |
      |"whPKUyIMOUyhmoyBjQW112345" |"whPKUyIMOUyhmoyBjQW112345"| "FakeName" | "FakeName"|"fake street"| "fake city"|"fake state" |
      |"1234567" | "12" | "FakeName" | "FakeName"|"fake street"| "fake city"|"fake state" |
      |"12345(67" | "12345(67" | "FakeName" | "FakeName"|"fake street"| "fake city"|"fake state" |