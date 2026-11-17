package com.swaglabs.steps;

import com.swaglabs.drivers.DriverFactory;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.pages.ProductsPage;
import com.swaglabs.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {
    private final LoginPage loginPage = new LoginPage();
    private final ProductsPage productsPage = new ProductsPage();

    @Given("I am on the Swag Labs login page")
    public void iAmOnTheSwagLabsLoginPage() {
        DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should see login outcome {string} with expected message {string}")
    public void iShouldSeeLoginOutcomeWithExpectedMessage(String outcome, String expectedMessage) {
        if ("success".equalsIgnoreCase(outcome)) {
            Assert.assertTrue(
                    productsPage.getPageTitleText().equalsIgnoreCase(expectedMessage),
                    "Expected title to be: " + expectedMessage + " but got: " + productsPage.getPageTitleText()
            );
        } else {
            Assert.assertTrue(
                    loginPage.getErrorMessage().contains(expectedMessage),
                    "Expected error message to contain: " + expectedMessage + " but got: " + loginPage.getErrorMessage()
            );
        }
    }

    @When("I logout from the application")
    public void iLogoutFromTheApplication() {
        productsPage.logout();
    }

    @Then("I should be redirected back to the login page")
    public void iShouldBeRedirectedBackToTheLoginPage() {
        Assert.assertTrue(loginPage.isLoginButtonDisplayed());
    }
}