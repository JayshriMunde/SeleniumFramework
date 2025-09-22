package PageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Checkout extends AbstractComponent {
    WebDriver driver;
    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProduct;
    @FindBy(xpath = "//button[contains(text(),'Checkout')]")
    WebElement Checkout;

    public Checkout(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkCart(String productName) {
        goToCart();
        Boolean match = cartProduct.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName));
        return match;
    }

    public PlaceOrder checkoutProduct() {
        Checkout.click();
        PlaceOrder placeorders = new PlaceOrder(driver);
        return placeorders;
    }


}
