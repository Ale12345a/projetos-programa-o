package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage {
    WebDriver driver;

    By dropdown = By.id("dropdown");

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectOption(String option) {
        new Select(driver.findElement(dropdown))
                .selectByVisibleText(option);
    }

    public String getSelected() {
        return new Select(driver.findElement(dropdown))
                .getFirstSelectedOption().getText();
    }
}