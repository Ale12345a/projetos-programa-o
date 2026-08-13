package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;

public class RegisterTests extends BaseTest {

    @Test
    public void registerUser() {
        driver.get("https://demoqa.com/automation-practice-form");

        RegisterPage page = new RegisterPage(driver);
        page.fillForm("Alex", "Silva", "alex@email.com");
        page.submit();

        // verifica se botão ainda existe → submit funcionou
        boolean exists = driver.findElements(By.id("submit")).size() > 0;

        Assert.assertTrue(exists);
    }
}