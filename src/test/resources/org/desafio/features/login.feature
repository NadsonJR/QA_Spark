@login @Web @All
Feature: Example feature

  @Login001
  Scenario: TC0001 - Open home page
    Given I open the login page
    And I should see the title "Swag Labs"
    Then I should see the text "Login"

  Scenario: TC0002 - Do login with success
    Given I open the login page
    And fill username input "standard_user"
    And fill password input "secret_sauce"
    And click on btn login
    Then Validate if user is logged in

  Scenario: TC0003 - Do login with error
    Given I open the login page
    And fill username input "standard_user"
    And fill password input "123"
    And click on btn login
    Then Validate error login message
