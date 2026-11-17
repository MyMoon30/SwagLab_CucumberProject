package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> cartItemNames;

    public List<String> getCartItems() {
        List<String> items = new ArrayList<>();
        for (WebElement el : cartItemNames) {
            items.add(el.getText());
        }
        return items;
    }

    public void removeItemFromCartPage(String productName) {
        String xpath = String.format(
            "//div[text()='%s']/ancestor::div[@class='cart_item']//button",
            productName
        );
        click(driver.findElement(By.xpath(xpath)));
    }

    public void clickCheckout() {
        click(checkoutButton);
    }

    public void clickContinueShopping() {
        click(continueShoppingButton);
    }
}
