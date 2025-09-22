package PageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PlaceOrder extends AbstractComponent {
    WebDriver driver;

    public PlaceOrder(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement countryDropdown;

    @FindBy(css = ".ta-results .ta-item")
    List<WebElement> countryList;


    public void selectCountry(String countryname) {
        countryDropdown.sendKeys(countryname);

        countryList.stream()
                .filter(country -> country.getText().trim().equalsIgnoreCase("India"))
                .findFirst()
                .ifPresent(WebElement::click);

    }

    @FindBy(css = ".action__submit")
    WebElement order;

    public void placeOrder() {
        order.click();
    }

    @FindBy(css =".hero-primary")
    WebElement message;

    public String confirmMessage(){

        return message.getText();
    }

}
