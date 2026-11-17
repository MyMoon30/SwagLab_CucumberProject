package com.swaglabs.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement successHeader;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "summary_subtotal_label")
    private WebElement itemTotalLabel;

    public void enterCustomerDetails(String fName, String lName, String zip) {
        type(firstNameField, fName);
        type(lastNameField, lName);
        type(postalCodeField, zip);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public void clickFinish() {
        click(finishButton);
    }

    public String getSuccessMessage() {
        return getText(successHeader);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public double getItemSubtotal() {
        String text = getText(itemTotalLabel); // e.g., "Item total: $39.98"
        return Double.parseDouble(text.replace("Item total: $", ""));
    }
}
