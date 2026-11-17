package com.swaglabs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> itemPrices;

    public String getPageTitleText() {
        return getText(pageTitle);
    }

    public void addProductToCart(String productName) {
        String xpath = String.format(
                "//div[normalize-space()=\"%s\"]/ancestor::div[@class='inventory_item']//button[contains(@id,'add-to-cart')]",
                productName
        );
        click(driver.findElement(By.xpath(xpath)));
    }

    public void removeProductFromCart(String productName) {
        String xpath = String.format(
                "//div[normalize-space()=\"%s\"]/ancestor::div[@class='inventory_item']//button[contains(@id,'remove')]",
                productName
        );
        click(driver.findElement(By.xpath(xpath)));
    }

    public void sortBy(String sortOption) {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(sortOption);
    }

    public List<String> getAllProductNames() {
        List<String> names = new ArrayList<>();
        for (WebElement el : itemNames) {
            names.add(el.getText());
        }
        return names;
    }

    public List<Double> getAllProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement el : itemPrices) {
            prices.add(Double.parseDouble(el.getText().replace("$", "")));
        }
        return prices;
    }

    public int getCartBadgeCount() {
        if (!isElementDisplayed(By.className("shopping_cart_badge"))) {
            return 0;
        }
        return Integer.parseInt(getText(cartBadge));
    }

    public void goToCart() {
        click(cartIcon);
    }

    public void logout() {
        click(menuButton);
        click(logoutLink);
    }
}