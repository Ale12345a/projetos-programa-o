package pages;

import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class BasePage {

    protected WebDriver driver;
    protected WaitUtils wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }
}