package PageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;
    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "userEmail")
    WebElement userName;

    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(id = "login")
    WebElement login;


    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/");

    }

    public ProductCatalogs logins(String name, String pass) {
        userName.sendKeys(name);
        password.sendKeys(pass);
        login.click();
         ProductCatalogs products = new ProductCatalogs(driver);
         return products;
    }

    @FindBy(css ="[class*='flyInOut']")
    WebElement errMessage;
    public String errorMessage(){
        waitWebElementVisible(errMessage);
        return errMessage.getText();
    }
}
