package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected WebDriver driver; //protected or public

    @BeforeMethod(groups = {"regression","smoke"})
    public void setUp(){
        driver = Driver.getDriver();
    }

    @AfterMethod(groups = {"regression","smoke"})
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
