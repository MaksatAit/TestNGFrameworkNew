package homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestBase;

public class LeftCornerButton extends TestBase {

    @Test
    public void test1(){
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        driver.findElement(By.cssSelector("input[id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce"+ Keys.ENTER);
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("about_sidebar_link")).click();

//        JavascriptExecutor js = (JavascriptExecutor)driver;
//        js.executeScript("window.scrollBy(0,10000)");

        Actions act = new Actions(driver);

        WebElement hoverOver = driver.findElement(By.xpath("//div[@data-hover-content='Solutions'][1]"));
//        act.moveToElement(hoverOver).build().perform();
    }
}
