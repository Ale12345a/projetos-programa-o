package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown(ITestResult result) throws Exception {

        if (!result.isSuccess() && driver != null) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            Files.createDirectories(Paths.get("screenshots"));

            String filename = "screenshots/" + result.getName() + "_" + System.currentTimeMillis() + ".png";

            Files.copy(src.toPath(), Paths.get(filename));
        }

        if (driver != null) {
            driver.quit();
        }
    }
}