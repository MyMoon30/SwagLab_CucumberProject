package com.swaglabs.steps;

import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.ProductsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryAndCartSteps {
    private final ProductsPage productsPage = new ProductsPage();
    private final CartPage cartPage = new CartPage();

    @When("I sort products by {string}")
    public void iSortProductsBy(String sortOption) {
        productsPage.sortBy(sortOption);
    }

    @Then("the products should be ordered properly according to {string}")
    public void theProductsShouldBeOrderedProperlyAccordingTo(String sortOption) {
        if (sortOption.contains("Name")) {
            List<String> actualNames = productsPage.getAllProductNames();
            List<String> expectedNames = new ArrayList<>(actualNames);
            if (sortOption.contains("A to Z")) {
                Collections.sort(expectedNames);
            } else {
                expectedNames.sort(Collections.reverseOrder());
            }
            Assert.assertEquals(actualNames, expectedNames);
        } else if (sortOption.contains("Price")) {
            List<Double> actualPrices = productsPage.getAllProductPrices();
            List<Double> expectedPrices = new ArrayList<>(actualPrices);
            if (sortOption.contains("low to high")) {
                Collections.sort(expectedPrices);
            } else {
                expectedPrices.sort(Collections.reverseOrder());
            }
            Assert.assertEquals(actualPrices, expectedPrices);
        }
    }

    @When("I add the following products to the cart:")
    public void iAddTheFollowingProductsToTheCart(DataTable dataTable) {
        List<String> products = dataTable.asList();
        for (String product : products) {
            productsPage.addProductToCart(product);
        }
    }

    @And("I remove {string} from the products page")
    public void iRemoveFromTheProductsPage(String productName) {
        productsPage.removeProductFromCart(productName);
    }

    @Then("the shopping cart badge count should be {int}")
    public void theShoppingCartBadgeCountShouldBe(int expectedCount) {
        Assert.assertEquals(productsPage.getCartBadgeCount(), expectedCount);
    }

    @When("I open the shopping cart")
    public void iOpenTheShoppingCart() {
        productsPage.goToCart();
    }

    @Then("the cart should contain {string}")
    public void theCartShouldContain(String productName) {
        Assert.assertTrue(cartPage.getCartItems().contains(productName));
    }
}
