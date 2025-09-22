package TestPractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class FlipkartDemo {


    WebDriver driver;

    @Test
    public void Login(){
        WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
        driver.get("https://www.flipkart.com/");
    }

    @Test(dependsOnMethods = "Login")
    public void SearchProduct(){
        driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']")).sendKeys("Mobiles");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']")).sendKeys(Keys.ENTER);

        List<WebElement> productList = driver.findElements(By.className("KzDlHZ"));
        System.out.println("Total products found: " + productList.size());

        for(WebElement products : productList){
            String productName = products.getText();
            if(productName.equalsIgnoreCase("Samsung Galaxy F05 (Twilight Blue, 64 GB)")){
                products.click();
                break;
            }
        }


    }

    @Test(dependsOnMethods = "SearchProduct")
    public void AddToCart(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for(String window : allWindows){
            if(!window.equals(parentWindow)){
                driver.switchTo().window(window);
                break;
            }
        }

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement addtocartbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add to cart']")));


        addtocartbtn.click();
       String message =  driver.findElement(By.cssSelector(".lHfxO4")).getText();

        Assert.assertEquals(message,"Delivery by Fri Sep 12");

    }
}
