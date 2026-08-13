package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By finishBtn = By.id("finish");
    private By successMsg = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillCheckoutForm(String fName, String lName, String zip) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName))
                .sendKeys(fName);

        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(postalCode).sendKeys(zip);

        driver.findElement(continueBtn).click();
    }

    public void finishOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    public boolean isOrderCompleted() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(successMsg)
        ).isDisplayed();
    }
}