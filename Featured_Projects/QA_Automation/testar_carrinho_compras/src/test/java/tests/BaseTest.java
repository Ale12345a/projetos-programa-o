package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}