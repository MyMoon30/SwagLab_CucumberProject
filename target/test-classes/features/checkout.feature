@checkout
Feature: End-to-End Checkout Workflow and Form Validation

  Background:
    Given I am on the Swag Labs login page
    And I login with username "standard_user" and password "secret_sauce"

  @e2e @smoke
  Scenario Outline: Complete end-to-end purchase with multiple products and customer data
    When I add products "<product_one>" and "<product_two>" to the cart
    And I proceed from cart to checkout
    And I enter checkout details with first name "<first_name>", last name "<last_name>", and postal code "<postal_code>"
    And I complete the order
    Then I should see the order confirmation message "Thank you for your order!"

    Examples:
      | product_one             | product_two                       | first_name | last_name | postal_code |
      | Sauce Labs Backpack     | Sauce Labs Bike Light             | Mymoon     | Beevi     | 625001      |
      | Sauce Labs Bolt T-Shirt | Sauce Labs Fleece Jacket          | Priya      | Sharma    | 400001      |
      | Sauce Labs Onesie       | Test.allTheThings() T-Shirt (Red) | Rahul      | Verma     | 560001      |

  @negative
  Scenario Outline: Checkout form mandatory field validation
    When I add products "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart
    And I proceed from cart to checkout
    And I enter checkout details with first name "<first_name>", last name "<last_name>", and postal code "<postal_code>"
    Then I should see checkout validation error "<error_message>"

    Examples:
      | first_name | last_name | postal_code | error_message                  |
      |            | Sharma    | 625001      | Error: First Name is required  |
      | Priya      |           | 625001      | Error: Last Name is required   |
      | Priya      | Sharma    |             | Error: Postal Code is required |
