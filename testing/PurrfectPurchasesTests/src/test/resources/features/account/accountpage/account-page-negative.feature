@acount-page
Feature: Account Page Negative

  Background: User is on home page
    Given User is on home page
    When User clicks the login link
    When User inputs "example1000@example.com" into the email field
    When User inputs "123456" into the password field
    When User clicks the login button
  Scenario Outline: User Edits their account info with wrong information
    When User clicks the account link
    When User clicks the edit button
    When User types in <fname> into fname field
    When User types in <lname> into lname field
    When User types in <streetAddress> into streetAddress field
    When User types in <city> into city field
    When User types in <state> into state field
    When User clicks the save button
    Then User should see an alert indicating something went wrong


    Examples:
      | fname | lname | streetAddress | city | state|
      | "tester"  |"McTest" |   ""      |"test city"|"test state"|
      | "tester"  |"McTest" |   "test street"      |""|"test state"|
      | "tester"  |"McTest" |   "test street"      |"test city"|""|
      | "testerwhPKUyIMOUyhmoyBjQW112345"  |"McTest" |   "test street"      |"test city"|"test state"|
      | "tester"  |"McTestwhPKUyIMOUyhmoyBjQW112345" |   "test street"      |"test city"|"test state"|
      | "tester"  |"McTest" |   "test streetwhPKUyIMOUyhmoyBjQW112345"      |"test city"|"test state"|
      | "tester"  |"McTest" |   "test street"      |"test citywhPKUyIMOUyhmoyBjQW112345"|"test statewhPKUyIMOUyhmoyBjQW112345"|



