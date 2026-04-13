package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    private WebDriver driver;

    private By firstAddToCart = By.cssSelector(".inventory_item button");
    private By cartIcon = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addFirstProductToCart() {
        driver.findElement(firstAddToCart).click();
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
}