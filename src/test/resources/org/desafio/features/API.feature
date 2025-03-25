@API
Feature: API
    Scenario: TC0001 - Get users
        Given I get users
        Then I should see the status code 200

    Scenario: TC0002 - Get user by id
        Given I get user by id 1
        Then I should see the status code 200

    Scenario: TC0003 - Get user by id not found
        Given I get user by id 1000
        Then I should see the status code 404

    Scenario: TC0004 - Create user
        Given I create a user
        Then I should see the status code 201

    Scenario: TC0005 - Update user
        Given I update a user
        Then I should see the status code 200

    Scenario: TC0006 - Delete user
        Given I delete a user
        Then I should see the status code 204