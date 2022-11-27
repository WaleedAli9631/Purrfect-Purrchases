@acountpage
Feature: Account Page Positive

  Background: User is on home page
    Given User is on home page
    When User clicks the login link
    When User inputs "example1000@example.com" into the email field
    When User inputs "123456" into the password field
    When User clicks the login button
  Scenario Outline: User Logins
    When User clicks the account link
    When User clicks the edit button
    When User types in <fname> into fname field
    When User types in <lname> into lname field
    When User types in <streetAddress> into streetAddress field
    When User types in <city> into city field
    When User types in <state> into state field
    When User clicks the save button
    Then User should see the changes reflected


    Examples:
      | fname | password |
      | "example1000@example.com"  |"123456" |

