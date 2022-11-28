@login
Feature: Login Negative

  Background: User is on home page
    Given User is on home page

  Scenario Outline: User Logins
    When User clicks the login link
    When User inputs <email> into the email field
    When User inputs <password> into the password field
    When User clicks the login button
    Then User should see an alert saying wrong information


    Examples:
      | email | password |
      | "example1000@example.com"  |"123eee456" |
      | "example10rrrrrr00@example.com"  |"123456" |
      | "example10eeeee00@example.com"  |"123eee456" |


