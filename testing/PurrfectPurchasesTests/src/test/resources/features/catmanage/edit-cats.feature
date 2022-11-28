Feature: Edit Cats

  Background: User is on Admin Page
    Given User is on home page
    Given User clicks Login Link
    Given User signs in as Admin
    Given User clicks on Admin link

  Scenario Outline: Edit Cats
    When User edits cat with <catID>
    When User inputs <catname> into Edit Name field
    When User inputs <breed> into Edit Breed field
    When User inputs <age> into Edit Age field
    When User inputs <gender> into Edit Gender field
    When User inputs <color> into Edit Color field
    When User inputs <cost> into Edit Cost field
    When User inputs <imageUrl> into Edit ImageURL field
    When User clicks Edit Cat submit button
    Then User should see alert edit confirmation
    Then User should see cat <catID> with <catname>,<breed>,<age>,<gender>,<color>,<cost>,and <imageUrl> in table


    Examples:
      | catID | catname | breed | age | gender | color | cost | imageUrl |
      | 5  |"Jimmy" | "Abyssian" | 5 | "Male" | "brown" | 50 | "assets/img/cat9.jpg" |
      | 11 |"" | "Abyssian" | 11 | "Female" | "brown" | 50 | "assets/img/cat12.jpg" |
      | 16 |"Thisismorethanfiftycharacterstotestthatthenamecannotbemorethanfifty" | "Abyssian" | 5 | "Female" | "brown" | 50 | "assets/img/cat3.jpg" |
      | 40 |"A" | "Abyssian" | 5 | "Female" | "brown" | 50 | "assets/img/cat3.jpg" |
      | 12 |"12" | "Abyssian" | 5 | "Female" | "brown" | 50 | "assets/img/cat3.jpg" |
      | 31 |"Caterine Hepburn" | "Abyssian" | 5 | "Male" | "Orange" | 50 | "assets/img/cat9.jpg" |
      | 21 |"Cat Stevens" | "Abyssian" | 11 | "Female" | "" | 50 | "assets/img/cat12.jpg" |
      | 7  |"Catherine Zeta Jones" | "Abyssian" | 5 | "Female" | "Thisismorethanfiftycharacterstotestthatthecolorcannotbemorethanfifty" | 50 | "assets/img/cat3.jpg" |
      | 3  |"Catherine O'Hara" | "Abyssian" | 5 | "Female" | "b" | 50 | "assets/img/cat3.jpg" |
      | 4  |"12" | "Abyssian" | 5 | "Female" | "15" | 50 | "assets/img/cat3.jpg" |