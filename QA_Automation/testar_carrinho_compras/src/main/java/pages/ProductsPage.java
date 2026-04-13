package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private By backpack = By.id("add-to-cart-sauce-labs-backpack");
    private By bikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void addBackpack() {
        driver.findElement(backpack).click();
    }

    public void addBikeLight() {
        driver.findElement(bikeLight).click();
    }

    public String getCartCount() {
        return driver.findElement(cartBadge).getText();
    }

    public boolean isCartEmpty() {
        return driver.findElements(cartBadge).isEmpty();
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }
}