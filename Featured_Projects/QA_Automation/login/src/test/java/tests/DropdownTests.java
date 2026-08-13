package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DropdownPage;

public class DropdownTests extends BaseTest {

    @Test
    public void selectDropdown() {
        driver.get("https://the-internet.herokuapp.com/dropdown");

        DropdownPage page = new DropdownPage(driver);
        page.selectOption("Option 1");

        Assert.assertEquals(page.getSelected(), "Option 1");
    }
}