Feature: Add Cats

  Background: User is on Admin Page
    Given User is on home page
    Given User clicks on Admin link

  Scenario Outline: Add Cats
    When User clicks Add Cat button
    When User inputs <catname> into Name field
    When User inputs <breed> into Breed field
    When User inputs <age> into Age field
    When User inputs <gender> into Gender field
    When User inputs <color> into Color field
    When User inputs <cost> into Cost field
    When User inputs <imageUrl> into ImageURL field
    When User clicks Add Cat submit button
    Then User should see alert confirmation
    Then User should see cat with <catname>,<breed>,<age>,<gender>,<color>,<cost>,and <imageUrl> in table
    When User logs out
    When User is on home page
    Then User should see cat with <catname>,<breed>,<age>,<gender>,<color>,<cost>,and <imageUrl> in homepage

    Examples:
      | catname | breed | age | gender | color | cost | imageUrl |
      | "Jimmy" | "Abyssian" | 5 | "Male" | "brown" | 50 | "assets/img/cat9.jpg" |
      | "" | "Abyssian" | 11 | "Female" | "brown" | 50 | "assets/img/cat12.jpg" |
      | "Thisismorethanfiftycharacterstotestthatthenamecannotbemorethanfifty" | "Abyssian" | 5 | "Female" | "brown" | 50 | "assets/img/cat3.jpg" |
      | "A" | "Abyssian" | 5 | "Female" | "brown" | 50 | "assets/img/cat3.jpg" |
      | "12" | "Abyssian" | 5 | "Female" | "brown" | 50 | "assets/img/cat3.jpg" |
      | "Caterine Hepburn" | "Abyssian" | 5 | "Male" | "Orange" | 50 | "assets/img/cat9.jpg" |
      | "Cat Stevens" | "Abyssian" | 11 | "Female" | "" | 50 | "assets/img/cat12.jpg" |
      | "Catherine Zeta Jones" | "Abyssian" | 5 | "Female" | "Thisismorethanfiftycharacterstotestthatthecolorcannotbemorethanfifty" | 50 | "assets/img/cat3.jpg" |
      | "Catherine O'Hara" | "Abyssian" | 5 | "Female" | "b" | 50 | "assets/img/cat3.jpg" |
      | "12" | "Abyssian" | 5 | "Female" | "15" | 50 | "assets/img/cat3.jpg" |