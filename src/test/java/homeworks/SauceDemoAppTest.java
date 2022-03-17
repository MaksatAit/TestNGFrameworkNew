package homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestBase;

public class SauceDemoAppTest extends TestBase {
    @Test(priority = 1)
    public void test1(){
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        driver.findElement(By.cssSelector("input[id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("invalid_password"+ Keys.ENTER);
        String actual = driver.findElement(By.tagName("h3")).getText();
        String expected = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(actual,expected);
    }
    @Test(priority = 2)
    public void test2(){
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce"+Keys.ENTER);
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        String strCart = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        int actual = Integer.parseInt(strCart);
        int expected =1;
        Assert.assertEquals(actual,expected);
    }
    @Test(priority = 3)
    public void test3(){
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce"+Keys.ENTER);
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.cssSelector("a[class='shopping_cart_link']")).click();
        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();
        String actual = driver.findElement(By.xpath("//div[@class='removed_cart_item']")).getAttribute("class");
        String expected ="removed_cart_item";
        Assert.assertEquals(actual,expected);
    }

}
