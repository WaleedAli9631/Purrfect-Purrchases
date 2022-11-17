Feature: Adoption Positive

  Background: Customer is on the Adoption page
    Given customer is on Adoption page

    Scenario: Selected cat on Adoption page
      Then customer sees cat they selected on the Adoption page
      Then customer will see Purchase Now button on Adoption page

    Scenario: Customer deletes selected cat
        Given customer sees cat they selected on the Adoption page
        Then customer sees REMOVE button
        When customer clicks the REMOVE button
        Then the cat should disappear from the Adoption page

    Scenario: Customer logs in or registers
      Given customer was not logged in
      # there might be more moving parts here. Should log in and register button disappear or not be visible
      # if a customer is already logged in?
      Then customer logs in or registers

    Scenario: Customer adopts cat
      When customer sees PURCHASE NOW button and clicks it
      When customer gets confirmation alert and confirms they want to buy the cat
      # not too sure about this last part
      Then customer gets order complete confirmation and the cat disappears from the cart area

