@login
Feature: Swag Labs Authentication & Session Management

  Background:
    Given I am on the Swag Labs login page

  @smoke @data-driven
  Scenario Outline: Data-driven login validation across valid, locked, and invalid accounts
    When I login with username "<username>" and password "<password>"
    Then I should see login outcome "<outcome>" with expected message "<message>"

    Examples:
      | username                | password     | outcome | message                                                                    |
      | standard_user           | secret_sauce | success | Products                                                                   |
      | problem_user            | secret_sauce | success | Products                                                                   |
      | performance_glitch_user | secret_sauce | success | Products                                                                   |
      | error_user              | secret_sauce | success | Products                                                                   |
      | visual_user             | secret_sauce | success | Products                                                                   |
      | locked_out_user         | secret_sauce | failure | Epic sadface: Sorry, this user has been locked out.                        |
      | invalid_user            | secret_sauce | failure | Epic sadface: Username and password do not match any user in this service |
      | standard_user           | wrong_pass   | failure | Epic sadface: Username and password do not match any user in this service |
      |                         | secret_sauce | failure | Epic sadface: Username is required                                         |
      | standard_user           |              | failure | Epic sadface: Password is required                                         |

  @smoke
  Scenario: Successful user logout
    When I login with username "standard_user" and password "secret_sauce"
    And I logout from the application
    Then I should be redirected back to the login page
