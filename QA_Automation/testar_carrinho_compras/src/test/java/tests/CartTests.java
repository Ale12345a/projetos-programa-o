package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class CartTests extends BaseTest {

    @Test
    public void addProduct() {
        driver.get("https://www.saucedemo.com/");

        new LoginPage(driver).login("standard_user", "secret_sauce");

        ProductsPage products = new ProductsPage(driver);
        products.addBackpack();

        Assert.assertEquals(products.getCartCount(), "1");
    }

    @Test
    public void addMultipleProducts() {
        driver.get("https://www.saucedemo.com/");

        new LoginPage(driver).login("standard_user", "secret_sauce");

        ProductsPage products = new ProductsPage(driver);
        products.addBackpack();
        products.addBikeLight();

        Assert.assertEquals(products.getCartCount(), "2");
    }

    @Test
    public void emptyCart() {
        driver.get("https://www.saucedemo.com/");

        new LoginPage(driver).login("standard_user", "secret_sauce");

        ProductsPage products = new ProductsPage(driver);

        Assert.assertTrue(products.isCartEmpty());
    }
}