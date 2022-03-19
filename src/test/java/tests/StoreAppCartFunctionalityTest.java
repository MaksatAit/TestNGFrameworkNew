package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.StoreAppCartPage;
import pages.StoreAppHomePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.io.IOException;

public class StoreAppCartFunctionalityTest extends TestBase {

    @Test
    public void validateAddCartFunctionalityTest() throws InterruptedException, IOException {
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        BrowserUtils.scrollUpOrDown(600);

        StoreAppHomePage homePage = new StoreAppHomePage();
        BrowserUtils.hoverOver(homePage.product1);

        homePage.product1AddCart.click();
        Thread.sleep(8000);
        BrowserUtils.takeScreenShot();
        String actual = homePage.addCartSuccessMessage.getText();
        String expected ="Product successfully added to your shopping cart";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual,expected);

        String addedProductNameText = homePage.addedProductName.getText();
        homePage.closeWindowIcon.click();
        homePage.cartButton.click();


        StoreAppCartPage cartPage = new StoreAppCartPage();
        String addedProductNameInCart = cartPage.productName.getText();
        softAssert.assertEquals(addedProductNameInCart,addedProductNameText);
        softAssert.assertAll();
    }
}
