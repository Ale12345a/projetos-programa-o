package tests.web;

import org.testng.annotations.Test;
import tests.BaseTest;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void validLoginTest() {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");
    }
}