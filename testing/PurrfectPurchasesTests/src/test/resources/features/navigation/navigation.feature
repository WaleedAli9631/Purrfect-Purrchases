@navigation
Feature: Navigation

  Background: User is on home page
    Given User is on home page

  Scenario: User clicks the login link and goes back
    When User clicks the login link
    When User clicks off screen of the modal
    Then The user should not see the modal

  Scenario: User clicks the registration link and goes back
    When User clicks the register link
    When User clicks off screen of the modal
    Then The user should not see the modal

  Scenario: User clicks the cart link and goes back
    When User clicks the cart link
    When User goes back
    Then The user should be on the home page

  Scenario: User clicks the account link and goes back
    When User clicks the login link
    When User inputs "example1000@example.com" into the email field
    When User inputs "123456" into the password field
    When User clicks the login button
    When User clicks the account link
    When User goes back
    Then The user should be on the home page