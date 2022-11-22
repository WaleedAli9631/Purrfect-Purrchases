Feature: View Cats

  Background: User is on Homepage
    Given User is on home page

  Scenario Outline: Filter Cats
    When User selects <breed> for breed dropdown
    When User selects <gender> for gender dropdown
    When User selects <age> for age dropdown
    When User clicks filter dropdown
    Then User should only see cats with <breed>,<gender>, and <age>

    Examples:
      | breed       | gender   | age   |
      | "Breed"     | "Gender" | "Age" |
      | "ALL"       | "ALL"    | "ALL" |
      | "Abyssian"  | "Male"   | "1"   |
      | "Himalayan" | "Female" | "20"  |
      | "Tabby"     | "Male"   | "15"  |
