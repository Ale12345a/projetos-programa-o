package pages;

import org.openqa.selenium.*;

public class UploadPage {
    WebDriver driver;

    By uploadInput = By.id("file-upload");
    By uploadBtn = By.id("file-submit");
    By success = By.tagName("h3");

    public UploadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void uploadFile(String path) {
        driver.findElement(uploadInput).sendKeys(path);
        driver.findElement(uploadBtn).click();
    }

    public String getMessage() {
        return driver.findElement(success).getText();
    }
}