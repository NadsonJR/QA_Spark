@Web @Checkout @All

Feature: Checkout feature

  @end-to-end
  Scenario: TC0004 - Buying Bike Light
    Given I open the Swag Labs
    And Login on Swag Labs
    And Add the Sauce Bike Light to cart
    And Checkout the product
    Then Confirm the order