@API @All
Feature: API

    @api-001
    Scenario Outline: TC0001 - Get API resources
        Given I get "<resource>"
        Then I should see the status code <status_code>
        Examples:
            | resource | status_code |
            | users    | 200         |
            | posts    | 200         |

    @api-002
    Scenario Outline: TC0002 - Get resource by ID
        Given I get "<resource>" by id <id>
        Then I should see the status code <status_code>
        Examples:
            | resource | id   | status_code |
            | users    | 1    | 200         |
            | users    | 1000 | 404         |

    @api-003
    Scenario Outline: TC0004 - Create user
        Given I create a "<resource>" with the following data
            | email  | password     |
            | eve.holt@reqres.in  | pistol |
        Then I should see the status code <status_code>
        Examples:
            | resource | status_code |
            | users    | 201         |

    # @api-007
        # Scenario: TC0007 - Create Update Delete user
        #     Given I create a user
        #     Then I should see the status code 201
        #     Given I update last created user
        #     Then I should see the status code 200
        #     Given I delete last created user
        #     Then I should see the status code 204
