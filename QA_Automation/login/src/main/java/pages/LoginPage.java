package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    By user = By.id("username");
    By pass = By.id("password");
    By btn = By.cssSelector("button[type='submit']");
    By msg = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String u, String p) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(user)).sendKeys(u);
        driver.findElement(pass).sendKeys(p);
        driver.findElement(btn).click();
    }

    public String getMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(msg)).getText();
    }
}