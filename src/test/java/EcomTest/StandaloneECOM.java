package EcomTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandaloneECOM {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String productName= "ZARA COAT 3";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("userEmail")).sendKeys("mundejayshri123@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Pass1234");
        driver.findElement(By.id("login")).click();



        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement productLists = products.stream().filter(product->
         product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

        productLists.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartProduct = driver.findElements(By.cssSelector(".cartSection h3"));
       Boolean match =  cartProduct.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();

        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("INDIA");

        List<WebElement> countryList = driver.findElements(By.cssSelector(".ta-results .ta-item"));

      /*  for (WebElement country : countryList) {
            if (country.getText().equalsIgnoreCase("India")) {
                country.click();
                break; // exit loop after clicking
            }
        }*/
        countryList.stream()
                .filter(country -> country.getText().trim().equalsIgnoreCase("India"))
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElement(By.cssSelector(".action__submit")).click();
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order"));

    }
}
