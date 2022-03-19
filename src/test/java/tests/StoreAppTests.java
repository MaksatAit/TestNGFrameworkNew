package tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.StoreAppCreateAccountPage;
import pages.StoreAppHomePage;
import pages.StoreAppLoginPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.Random;

public class StoreAppTests extends TestBase {

    String email;
    String passwordSignIn;

    @DataProvider(name = "registerData")
    public static Object[][] registerData(){
        Object[][] data = new Object[][]{
                {"Patel","Harsh","password","1","1","2001","123 My Road","Chicago","13","12345","123456789","Home address"},
                {"Kim","Yan","QWERty123","25","4","2005","1 State st.","New York","32","43221","123456444","Work address"},
                {"John","Doe","Gam3221","2","4","1950"," 4100 Oak st","Los Angeles","5","54321","123456666","Work address"}
        };
        return data;
    }

    @Test(dataProvider = "registerData",groups = {"regression","smoke"})
    public void validateRegisterFunctionalityTest(String fistName, String lastName,String password,String birthDay,
                                                  String birthMonth,String birthYear,String address,
                                                  String city,String state, String zip,String phone,String reference){
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        StoreAppHomePage homePage = new StoreAppHomePage();
        homePage.loginButton.click();

        StoreAppLoginPage loginPage = new StoreAppLoginPage();
        Random random = new Random();
        int randomNum=random.nextInt();
        email=randomNum+"maksat@gmail.com";
        loginPage.registerEmailBox.sendKeys(randomNum+"maksat@gmail.com");
        loginPage.createAccountButton.click();

        StoreAppCreateAccountPage createAccountPage = new StoreAppCreateAccountPage();
        createAccountPage.firstName.sendKeys(fistName);
        createAccountPage.lastName.sendKeys(lastName);
        passwordSignIn=password;
        createAccountPage.password.sendKeys(password);
        BrowserUtils.selectDropdownByValue(createAccountPage.birthDay,birthDay);
        BrowserUtils.selectDropdownByValue(createAccountPage.birthMonth,birthMonth);
        BrowserUtils.selectDropdownByValue(createAccountPage.birthYear,birthYear);
        createAccountPage.address.sendKeys(address);
        createAccountPage.city.sendKeys(city);
        BrowserUtils.selectDropdownByValue(createAccountPage.state,state);
        createAccountPage.zip.sendKeys(zip);
        createAccountPage.phone.sendKeys(phone);
        createAccountPage.reference.sendKeys(reference);
        createAccountPage.registerButton.click();

        String actualTitle=driver.getTitle();
        String expectedTitle="My account - My Store";
        Assert.assertEquals(actualTitle,expectedTitle);
    }
    @Test(dependsOnMethods = "validateRegisterFunctionalityTest",groups = {"regression","smoke"})
    public void validateSignInFunctionalityTest(){
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        StoreAppHomePage homePage = new StoreAppHomePage();
        homePage.loginButton.click();

        StoreAppLoginPage loginPage = new StoreAppLoginPage();
        loginPage.signIn(email,passwordSignIn);
        String actualTitle=driver.getTitle();
        String expectedTitle="My account - My Store";
        Assert.assertEquals(actualTitle,expectedTitle);
    }
}














