@inventory
Feature: Product Sorting and Shopping Cart Management

  Background:
    Given I am on the Swag Labs login page
    And I login with username "standard_user" and password "secret_sauce"

  @sorting
  Scenario Outline: Verify inventory sorting options
    When I sort products by "<sort_option>"
    Then the products should be ordered properly according to "<sort_option>"

    Examples:
      | sort_option         |
      | Name (A to Z)       |
      | Name (Z to A)       |
      | Price (low to high) |
      | Price (high to low) |

  @cart
  Scenario: Add multiple items using DataTable and remove one item
    When I add the following products to the cart:
      | Sauce Labs Backpack          |
      | Sauce Labs Bike Light        |
      | Sauce Labs Bolt T-Shirt      |
      | Test.allTheThings() T-Shirt (Red) |
    Then the shopping cart badge count should be 4
    When I remove "Sauce Labs Bike Light" from the products page
    Then the shopping cart badge count should be 3
    When I open the shopping cart
    Then the cart should contain "Sauce Labs Backpack"
