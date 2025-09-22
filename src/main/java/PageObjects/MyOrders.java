package PageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.text.html.CSS;
import java.util.List;

public class MyOrders extends AbstractComponent {
    WebDriver driver;
    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> myOrdersName;

    public MyOrders(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean myOrdersPage(String productName) {
        varifyMyOrdersDisplay();
        Boolean match = myOrdersName.stream().anyMatch(cart -> cart.getText().equalsIgnoreCase(productName));
        return match;
    }


}
