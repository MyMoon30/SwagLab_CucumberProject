package com.swaglabs.steps;

import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.CheckoutPage;
import com.swaglabs.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutSteps {
    private final ProductsPage productsPage = new ProductsPage();
    private final CartPage cartPage = new CartPage();
    private final CheckoutPage checkoutPage = new CheckoutPage();

    @When("I add products {string} and {string} to the cart")
    public void iAddProductsAndToTheCart(String prod1, String prod2) {
        productsPage.addProductToCart(prod1);
        productsPage.addProductToCart(prod2);
    }

    @And("I proceed from cart to checkout")
    public void iProceedFromCartToCheckout() {
        productsPage.goToCart();
        cartPage.clickCheckout();
    }

    @And("I enter checkout details with first name {string}, last name {string}, and postal code {string}")
    public void iEnterCheckoutDetails(String fName, String lName, String zip) {
        checkoutPage.enterCustomerDetails(fName, lName, zip);
        checkoutPage.clickContinue();
    }

    @And("I complete the order")
    public void iCompleteTheOrder() {
        Assert.assertTrue(checkoutPage.getItemSubtotal() > 0, "Subtotal should be greater than 0");
        checkoutPage.clickFinish();
    }

    @Then("I should see the order confirmation message {string}")
    public void iShouldSeeTheOrderConfirmationMessage(String expectedMessage) {
        Assert.assertEquals(checkoutPage.getSuccessMessage(), expectedMessage);
    }

    @Then("I should see checkout validation error {string}")
    public void iShouldSeeCheckoutValidationError(String expectedError) {
        Assert.assertTrue(checkoutPage.getErrorMessage().contains(expectedError));
    }
}
