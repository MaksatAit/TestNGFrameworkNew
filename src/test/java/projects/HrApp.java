package projects;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestBase;

public class HrApp extends TestBase {

    @Test(priority = 1)
    public void mainPage(){
        driver.get(ConfigReader.getProperty("HrApp"));
        String actualLoginMessage = driver.findElement(By.tagName("h1")).getText();
        String expectedLoginMessage = "Login";
        Assert.assertEquals(actualLoginMessage,expectedLoginMessage);
    }

    @Test(priority = 2)
    public void loginInButton(){
        driver.get(ConfigReader.getProperty("HrApp"));
        driver.findElement(By.name("username")).sendKeys("Mindtek");
        driver.findElement(By.name("password")).sendKeys("MindtekStudent");
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
        String actual = driver.findElement(By.xpath("//label[@for='departments']")).getText();
        String expected = "Select Department";
        Assert.assertEquals(actual,expected);
    }

    @Test(priority = 3)
    public void logOutButtonTest(){
        driver.get(ConfigReader.getProperty("HrApp"));
        driver.findElement(By.name("username")).sendKeys("Mindtek");
        driver.findElement(By.name("password")).sendKeys("MindtekStudent");
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
        driver.findElement(By.xpath("(//a[@class='nav-link'])[4]")).click();
        String actual = driver.findElement(By.tagName("h1")).getText();
        String expected = "You are logged out";
        Assert.assertEquals(actual,expected);
    }
    @Test(priority = 4)
    public void invalidUserNameTestCase(){
        driver.get(ConfigReader.getProperty("HrApp"));
        driver.findElement(By.name("username")).sendKeys("mindtek");
        driver.findElement(By.name("password")).sendKeys("MindtekStudent");
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
        String actual = driver.findElement(By.xpath("//div[@class='alert alert-waring']")).getText();
        String expected = "Invalid credentials";
        Assert.assertEquals(actual,expected);
    }
    @Test(priority = 5)
    public void invalidPasswordTestCase(){
        driver.get(ConfigReader.getProperty("HrApp"));
        driver.findElement(By.name("username")).sendKeys("Mindtek");
        driver.findElement(By.name("password")).sendKeys("mindtekstudent");
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
        String actual = driver.findElement(By.xpath("//div[@class='alert alert-waring']")).getText();
        String expected = "Invalid credentials";
        Assert.assertEquals(actual,expected);
    }
    @Test(priority = 6)
    public void invalidUserNameAndPassword(){
        driver.get(ConfigReader.getProperty("HrApp"));
        driver.findElement(By.name("username")).sendKeys("MINDTEK");
        driver.findElement(By.name("password")).sendKeys("MINDTEKSTUDENT");
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
        String actual = driver.findElement(By.xpath("//div[@class='alert alert-waring']")).getText();
        String expected = "Invalid credentials";
        Assert.assertEquals(actual,expected);
    }
    @Test(priority = 7)
    public void test1(){
        driver.get(ConfigReader.getProperty("HrApp"));
        driver.findElement(By.name("username")).sendKeys("Mindtek");
        String displayedUserName = driver.findElement(By.xpath("(//div[@class='container'])[2]")).getText();
        String actual = displayedUserName.substring(displayedUserName.lastIndexOf(":")+1,displayedUserName.lastIndexOf(" "));
        String expected = " Mindtek";
        Assert.assertEquals(actual,expected);
    }
}
