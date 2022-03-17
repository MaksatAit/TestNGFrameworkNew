package pages.homeworkpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class GuruDemoPages {
    public GuruDemoPages(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "email")
    public WebElement email;

    @FindBy(id = "passwd")
    public WebElement password;

    @FindBy(id = "SubmitLogin")
    public WebElement signInButton;

    @FindBy(tagName = "h3")
    public WebElement message;

    @FindBy(css = "a[href='http://demo.guru99.com/payment-gateway/index.php']")
    public WebElement paymentGateway;

    @FindBy(xpath = "//input[@value='Buy Now']")
    public WebElement buyNow;

    @FindBy(tagName = "h3")
    public WebElement price1;

    @FindBy(xpath = "//div[@class='6u 12u$(xsmall)']")
    public WebElement price2;

    @FindBy(id = "card_nmuber")
    public WebElement cardNumber;

    @FindBy(id = "month")
    public WebElement expirationMonth;

    @FindBy(id = "year")
    public WebElement expirationYear;

    @FindBy(id = "cvv_code")
    public WebElement cvv_code;

    @FindBy(xpath = "//input[@name='submit']")
    public WebElement payButton;
}
