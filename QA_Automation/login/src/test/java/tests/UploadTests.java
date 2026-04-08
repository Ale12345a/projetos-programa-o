package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.UploadPage;

public class UploadTests extends BaseTest {

    @Test
    public void uploadFile() {
        driver.get("https://the-internet.herokuapp.com/upload");

        UploadPage page = new UploadPage(driver);
        page.uploadFile("/etc/hosts");

        Assert.assertEquals(page.getMessage(), "File Uploaded!");
    }
}