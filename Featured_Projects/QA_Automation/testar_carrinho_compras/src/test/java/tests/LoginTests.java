package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void loginSuccess() {
        driver.get("https://www.saucedemo.com/");

        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }
}