package PageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogs extends AbstractComponent {
    WebDriver driver;

    By product = By.cssSelector(".mb-3");
    By toastMessgae= By.cssSelector("#toast-container");




    public ProductCatalogs(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;


    public List<WebElement> getProductList() {
        waitElementVisible(product);
        return products;
    }

    public WebElement getProductName(String productname) {
        WebElement productNames = getProductList().stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
        return productNames;
    }

    @FindBy(css =".ng-animating")
    WebElement animation;

    public Checkout addToCart(String productName) throws InterruptedException {
        getProductName(productName).findElement(By.cssSelector(".card-body button:last-of-type")).click();
        waitElementVisible(toastMessgae);
        waitElementInvisible(animation);
        Thread.sleep(2000);
        Checkout checkouts = new Checkout(driver);
        return checkouts;
    }
}
