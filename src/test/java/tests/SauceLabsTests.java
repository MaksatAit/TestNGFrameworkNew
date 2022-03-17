package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.List;

public class SauceLabsTests extends TestBase {


    @Test(priority = 1,groups = {"regression","smoke"})
    public void validateLoginTest(){
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        SaucedemoLoginPage loginPage = new SaucedemoLoginPage();
        loginPage.login();

        String actualPageHeader = driver.findElement(By.xpath("//span[@class='title']")).getText();
        String expectedPageHeader = "PRODUCTS";
        Assert.assertEquals(actualPageHeader,expectedPageHeader);
    }
    @Test(priority = 2,groups = {"regression"})
    public void validateFilterByPriceTest(){
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        SaucedemoLoginPage loginPage = new SaucedemoLoginPage();
        loginPage.login();

        WebElement filterDropDown = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select select = new Select(filterDropDown);
        select.selectByValue("lohi");

        List<WebElement> prices = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

        for(int i=1; i<prices.size();i++){
            String price=prices.get(i).getText().substring(1);
            double priceDouble = Double.parseDouble(price);

            String price2=prices.get(i-1).getText().substring(1);
            double priceDouble2=Double.parseDouble(price2);

            Assert.assertTrue(priceDouble>=priceDouble2);
        }
    }
    @Test(priority = 3,groups = {"regression","smoke"})
    public void validateOrderFunctionalityTest(){
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        SaucedemoLoginPage loginPage = new SaucedemoLoginPage();
        loginPage.login();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.cssSelector("a[class='shopping_cart_link']")).click();
        String price = driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();

        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
        String checkOutPrice = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']")).getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(checkOutPrice.substring(checkOutPrice.lastIndexOf(" ")+1),price);

        driver.findElement(By.id("finish")).click();
        String actualText = driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
        String expectedText = "THANK YOU FOR YOUR ORDER";
        softAssert.assertEquals(actualText,expectedText);
        softAssert.assertAll();
    }
    @Test(priority = 4,groups = {"regression","smoke"})
    public void validateCheckoutTotalTest(){
        driver.get(ConfigReader.getProperty("SauceLabsURL"));
        SaucedemoLoginPage loginPage = new SaucedemoLoginPage();
        loginPage.login();

        SaucedemoHomePage homePage = new SaucedemoHomePage();
        String backpackPrice = homePage.backPackPrice.getText();
        String bikeLightPrice = homePage.bikeLightPrice.getText();
        homePage.backPackProduct.click();
        homePage.bikeLight.click();
        homePage.cart.click();

        SaucedemoByCartPage myCartPage = new SaucedemoByCartPage();
        myCartPage.checkoutButton.click();

        SaucedemoCheckoutPage checkoutPage = new SaucedemoCheckoutPage();
        checkoutPage.checkoutWithValidInfo();

        SaucedemoCheckoutOverviewPage checkoutOverviewPage = new SaucedemoCheckoutOverviewPage();
        String actualTotalPrice = checkoutOverviewPage.totalPrice.getText();

        double backpackPriceDouble = Double.parseDouble(backpackPrice.substring(1));
        double bikeLightPriceDouble = Double.parseDouble(bikeLightPrice.substring(1));
        double actualTotalPriceDouble = Double.parseDouble(actualTotalPrice.substring(actualTotalPrice.indexOf("$")+1));
        Assert.assertEquals(actualTotalPriceDouble,backpackPriceDouble+bikeLightPriceDouble);
    }
}
