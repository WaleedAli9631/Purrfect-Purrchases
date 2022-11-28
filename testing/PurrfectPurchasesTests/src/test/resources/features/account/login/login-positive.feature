@login
Feature: Login Positive

  Background: User is on home page
    Given User is on home page

  Scenario Outline: User Logins
    When User clicks the login link
    When User inputs <email> into the email field
    When User inputs <password> into the password field
    When User clicks the login button
    Then User should see the account page link
    Then User should see the logout button
    Then If user is an admin they should see the admin link <email> <password>

    Examples:
      | email | password |
      | "example1000@example.com"  |"123456" |
      | "admin@admin.com"  |"admin123" |

