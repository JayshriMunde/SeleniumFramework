package AbstractComponents;

import PageObjects.MyOrders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;
    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }


    public void waitElementVisible(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitWebElementVisible(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitElementInvisible(WebElement findBy){
        WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
        waits.until(ExpectedConditions.invisibilityOf(findBy));
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartLink;

    public void goToCart(){
        cartLink.click();
    }

    @FindBy(css = "[routerlink*='myorders']")
    WebElement myOrders;

    public MyOrders varifyMyOrdersDisplay(){
        myOrders.click();
        MyOrders orders = new MyOrders(driver);
        return orders;
    }


}
