package homeworks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BlazeDemo {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void test1(){
        driver.get("https://blazedemo.com/index.php");
        WebElement fromPort = driver.findElement(By.name("fromPort"));
        Select selectFrom = new Select(fromPort);
        selectFrom.selectByValue("San Diego");

        WebElement toPort = driver.findElement(By.name("toPort"));
        Select selectTo = new Select(toPort);
        selectTo.selectByValue("New York");

        WebElement findFlightsButton = driver.findElement(By.xpath("//input"));
        findFlightsButton.click();

        WebElement chooseFlightButton = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
        chooseFlightButton.click();

        driver.findElement(By.id("inputName")).sendKeys("David Clark");
        driver.findElement(By.id("address")).sendKeys("123 Washington ave.");
        driver.findElement(By.id("city")).sendKeys("Austin");
        driver.findElement(By.id("state")).sendKeys("TX");
        driver.findElement(By.id("zipCode")).sendKeys("12345");

        WebElement cartTypeDropDown  = driver.findElement(By.id("cardType"));
        Select cartType = new Select(cartTypeDropDown);
        cartType.selectByValue("amex");

        driver.findElement(By.id("creditCardNumber")).sendKeys("mycreditcardnumber");
        driver.findElement(By.id("city")).sendKeys("Austin");
        driver.findElement(By.id("city")).sendKeys("Austin");
        driver.findElement(By.id("city")).sendKeys("Austin");
        driver.findElement(By.id("city")).sendKeys("Austin");

        WebElement month = driver.findElement(By.id("creditCardMonth"));
        month.clear();
        month.sendKeys("11");

        WebElement year = driver.findElement(By.id("creditCardYear"));
        year.clear();
        year.sendKeys("2025");
        driver.findElement(By.id("nameOnCard")).sendKeys("David Clark");

        driver.findElement(By.cssSelector("input[class='btn btn-primary']")).click();

        String actualMessage = driver.findElement(By.tagName("h1")).getText();
        String expectedMessage = "Invalid credit card number! ";

        Assert.assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void test2(){

        driver.get("https://blazedemo.com/index.php");
        WebElement fromPort = driver.findElement(By.name("fromPort"));
        Select selectFrom = new Select(fromPort);
        selectFrom.selectByValue("San Diego");

        WebElement toPort = driver.findElement(By.name("toPort"));
        Select selectTo = new Select(toPort);
        selectTo.selectByValue("New York");

        WebElement findFlightsButton = driver.findElement(By.xpath("//input"));
        findFlightsButton.click();

        WebElement chooseFlightButton = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));
        chooseFlightButton.click();

        driver.findElement(By.id("inputName")).sendKeys("John Doe");
        driver.findElement(By.id("address")).sendKeys("123 Washington ave.");
        driver.findElement(By.id("city")).sendKeys("New York");
        driver.findElement(By.id("state")).sendKeys("NY");
        driver.findElement(By.id("zipCode")).sendKeys("12345");

        WebElement cartTypeDropDown  = driver.findElement(By.id("cardType"));
        Select cartType = new Select(cartTypeDropDown);
        cartType.selectByValue("visa");

        WebElement card = driver.findElement(By.id("creditCardNumber"));
        String cardNumber = "1234567890";
        card.sendKeys(cardNumber);

        System.out.println(card.getText());
        driver.findElement(By.id("city")).sendKeys("Austin");


        WebElement month = driver.findElement(By.id("creditCardMonth"));
        month.clear();
        month.sendKeys("11");

        WebElement year = driver.findElement(By.id("creditCardYear"));
        year.clear();
        year.sendKeys("2025");
        driver.findElement(By.id("nameOnCard")).sendKeys("John Doe");

        driver.findElement(By.cssSelector("input[class='btn btn-primary']")).click();

        String actualMessage = driver.findElement(By.tagName("h1")).getText();
        String expectedMessage = "Thank you for your purchase today!"; // intentionally deleted space

        //continuation of test2
        SoftAssert softAssert = new SoftAssert();
        /**5. Then validate success message "Thank you for your purchase today! "**/
        softAssert.assertEquals(actualMessage,expectedMessage);

        String expectedLastFourDigits = cardNumber.substring(cardNumber.length()-4);

        String card2 ="";
        List <WebElement> table = driver.findElements(By.xpath("//tbody/tr/td"));
        for (int i=0; i<table.size();i++){
            if(table.get(i).getText().equalsIgnoreCase("Card Number")){
                card2 = table.get(i+1).getText();
            }
        }
        String actualLastFourDigits = card2.substring(card2.length()-4);
        /**6. Then validate last 4 digits of card is matching with provided card**/
        softAssert.assertEquals(actualLastFourDigits,expectedLastFourDigits);

        String date = "";
        List <WebElement> table2 = driver.findElements(By.xpath("//tbody/tr/td"));
        for (int i=0; i<table2.size();i++){
            if(table2.get(i).getText().equalsIgnoreCase("Date")){
                date = table2.get(i+1).getText();
            }
        }

        Date todayDate = new Date();
        Date webDate = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.format(webDate);
        dateFormat.format(todayDate);
        /**7. Then validate date when it was booked is today's date**/
        softAssert.assertEquals(webDate.toString(),todayDate.toString());
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}


























