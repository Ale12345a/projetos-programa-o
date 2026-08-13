package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void loginSuccess() {
        driver.get("https://the-internet.herokuapp.com/login");

        LoginPage page = new LoginPage(driver);
        page.login("tomsmith", "SuperSecretPassword!");

        Assert.assertTrue(page.getMessage().contains("secure area"));
    }

    @Test
    public void loginFail() {
        driver.get("https://the-internet.herokuapp.com/login");

        LoginPage page = new LoginPage(driver);
        page.login("wrong", "wrong");

        Assert.assertTrue(page.getMessage().contains("invalid"));
    }
}