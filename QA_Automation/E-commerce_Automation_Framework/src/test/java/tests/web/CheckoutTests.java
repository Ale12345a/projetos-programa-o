package tests.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.CheckoutPage;

public class CheckoutTests {

    private WebDriver driver;

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void completeCheckoutTest() {

        // 1. LOGIN
        loginPage.login("standard_user", "secret_sauce");

        // 2. ADD PRODUCT
        productsPage.addFirstProductToCart();

        // 3. GO TO CART
        productsPage.goToCart();

        // 4. CHECKOUT
        cartPage.clickCheckout();

        // 5. FILL FORM
        checkoutPage.fillCheckoutForm("Alex", "Silva", "1234");

        // 6. FINISH ORDER
        checkoutPage.finishOrder();

        // 7. ASSERT
        Assert.assertTrue(checkoutPage.isOrderCompleted());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}