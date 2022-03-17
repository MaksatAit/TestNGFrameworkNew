package homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.homeworkpages.GuruDemoPages;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.concurrent.TimeUnit;

public class GuruDemoTests extends TestBase {

    @DataProvider(name = "loginData")
    public static Object[][] loginData(){
            Object[][] data = new Object[][]{
                    {"admin@mindtek.com","admin","1234567812345678","5","2025","999","3"}
            };
            return data;
    }

    @Test(dataProvider ="loginData")
    public void testCase1(String email, String password,String cardNumber, String expirationMonth,
                          String expirationYear, String cvv,String numberOfToys){
        driver.get(ConfigReader.getProperty("GuruDemo"));
        GuruDemoPages loginGuruDemoPage = new GuruDemoPages();
        loginGuruDemoPage.email.sendKeys(email);
        loginGuruDemoPage.password.sendKeys(password);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ccpa-consent-notice")));
        driver.switchTo().frame(driver.findElement(By.id("ccpa-consent-notice")));
        driver.findElement(By.id("close")).click();
        loginGuruDemoPage.signInButton.click();
        String actualMessage = loginGuruDemoPage.message.getText();
        String expectedMessage = "Successfully Logged in…";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualMessage,expectedMessage);
    }
    @Test(dataProvider = "loginData")
    public void testCase2(String email, String password,String cardNumber, String expirationMonth,
                          String expirationYear, String cvv,String numberOfToys) {
        driver.get(ConfigReader.getProperty("GuruDemo"));
        GuruDemoPages loginPage = new GuruDemoPages();
        loginPage.email.sendKeys(email);
        loginPage.password.sendKeys(password);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ccpa-consent-notice")));
        driver.switchTo().frame(driver.findElement(By.id("ccpa-consent-notice")));
        driver.findElement(By.id("close")).click();
        loginPage.signInButton.click();
        loginPage.paymentGateway.click();
        String priceOnBuyNowPage = loginPage.price1.getText().substring(loginPage.price1.getText().indexOf("$")+1);
        loginPage.buyNow.click();
        String priceOnCheckOutPage = loginPage.price2.getText().substring(loginPage.price2.getText().indexOf("$")+1);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(priceOnBuyNowPage,priceOnCheckOutPage);
        loginPage.cardNumber.sendKeys(cardNumber);
        BrowserUtils.selectDropdownByValue(loginPage.expirationMonth,expirationMonth);
        BrowserUtils.selectDropdownByValue(loginPage.expirationYear,expirationYear);
        loginPage.cvv_code.sendKeys(cvv);
        loginPage.payButton.click();
        String actual = driver.findElement(By.tagName("h2")).getText();
        String expected = "Payment Successful!";
        softAssert.assertEquals(actual,expected);
    }
    @Test(dataProvider = "loginData")
    public void testCase3(String email, String password,String cardNumber, String expirationMonth,
                          String expirationYear, String cvv,String numberOfToys){
        driver.get(ConfigReader.getProperty("GuruDemo"));
        GuruDemoPages loginPage = new GuruDemoPages();
        loginPage.email.sendKeys(email);
        loginPage.password.sendKeys(password);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ccpa-consent-notice")));
        driver.switchTo().frame(driver.findElement(By.id("ccpa-consent-notice")));
        driver.findElement(By.id("close")).click();
        loginPage.signInButton.click();
        loginPage.paymentGateway.click();
        WebElement quantityButton = driver.findElement(By.xpath("//select[@name='quantity']"));
        BrowserUtils.selectDropdownByValue(quantityButton,numberOfToys);
        String priceOnBuyNowPage = loginPage.price1.getText().substring(loginPage.price1.getText().indexOf("$")+1);
        int intPriceOnBuyNowPage = Integer.parseInt(priceOnBuyNowPage);
        int intNumberOfToys = Integer.parseInt(numberOfToys);
        double doubleExpectedTotal = intNumberOfToys*intPriceOnBuyNowPage;
        loginPage.buyNow.click();
        String strActual = loginPage.price2.getText().substring(loginPage.price2.getText().indexOf("$")+1);
        double doubleActual = Double.parseDouble(strActual);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(doubleActual,doubleExpectedTotal);
        softAssert.assertAll();
    }
    @Test(dataProvider = "loginData")
    public void testCase4(String email, String password,String cardNumber, String expirationMonth,
                          String expirationYear, String cvv,String numberOfToys){
        driver.get(ConfigReader.getProperty("GuruDemo"));
        GuruDemoPages loginPage = new GuruDemoPages();
        loginPage.email.sendKeys(email);
        loginPage.password.sendKeys(password);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ccpa-consent-notice")));
        driver.switchTo().frame(driver.findElement(By.id("ccpa-consent-notice")));
        driver.findElement(By.id("close")).click();
        loginPage.signInButton.click();
        loginPage.paymentGateway.click();
        loginPage.buyNow.click();
        loginPage.cardNumber.sendKeys("1234");
        BrowserUtils.selectDropdownByValue(loginPage.expirationMonth,expirationMonth);
        BrowserUtils.selectDropdownByValue(loginPage.expirationYear,expirationYear);
        loginPage.cvv_code.sendKeys(cvv);
        loginPage.payButton.click();
        String actualMessage = driver.switchTo().alert().getText();
        String expectedMessage = "Check card number is 16 digits!";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualMessage,expectedMessage);
    }
    @Test(dataProvider = "loginData")
    public void testCase5(String email, String password,String cardNumber, String expirationMonth,
                          String expirationYear, String cvv,String numberOfToys){
        driver.get(ConfigReader.getProperty("GuruDemo"));
        GuruDemoPages demoPages = new GuruDemoPages();
        demoPages.email.sendKeys(email);
        demoPages.password.sendKeys(password);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ccpa-consent-notice")));
        driver.switchTo().frame(driver.findElement(By.id("ccpa-consent-notice")));
        driver.findElement(By.id("close")).click();
        demoPages.signInButton.click();
        driver.findElement(By.xpath("//a[@href='http://demo.guru99.com/telecom/index.html']")).click();
        driver.findElement(By.tagName("h3")).click();
        driver.findElement(By.xpath("//label[@for='done']")).click();
        driver.findElement(By.id("fname")).sendKeys("Emrah");
        driver.findElement(By.id("lname")).sendKeys("Durmush");
        driver.findElement(By.id("email")).sendKeys(email);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='12u$']/textarea")).sendKeys("4343 W Oaks st");
        driver.findElement(By.id("telephoneno")).sendKeys("3235553366");
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        String actualMessage = driver.findElement(By.xpath("(//tbody/tr/td/b)[2]")).getText();
        String expectedMessage ="Please Note Down Your CustomerID";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualMessage,expectedMessage);
        WebElement id = driver.findElement(By.tagName("h3"));
        softAssert.assertTrue(id.isDisplayed());
        softAssert.assertAll();
    }
}
