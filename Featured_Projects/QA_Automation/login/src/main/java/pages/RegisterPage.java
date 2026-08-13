package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class RegisterPage {
    WebDriver driver;
    WebDriverWait wait;

    By firstName = By.id("firstName");
    By lastName = By.id("lastName");
    By email = By.id("userEmail");
    By submitBtn = By.id("submit");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillForm(String name, String surname, String mail) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(name);
        driver.findElement(lastName).sendKeys(surname);
        driver.findElement(email).sendKeys(mail);
    }

    public void submit() {
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(submitBtn));

        // scroll até ao botão
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

        try {
            // tentativa normal
            wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        } catch (Exception e) {
            // fallback profissional (JS click)
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }
}