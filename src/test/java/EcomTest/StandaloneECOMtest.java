package EcomTest;

import ECOM.TestComponents.BaseTest;
import PageObjects.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class StandaloneECOMtest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test
    public void ConfirmOrder() throws IOException, InterruptedException {
        ProductCatalogs products = page.logins("mundejayshri123@gmail.com", "Pass1234");
        List<WebElement> productsList = products.getProductList();
        Checkout checkouts = products.addToCart(productName);
        Boolean match = checkouts.checkCart(productName);
        Assert.assertTrue(match);
        PlaceOrder placeorders = checkouts.checkoutProduct();
        placeorders.selectCountry("India");
        placeorders.placeOrder();
        String confirmMessage = placeorders.confirmMessage();
        System.out.println(confirmMessage);
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the orde"));
    }

    @Test(dependsOnMethods = {"ConfirmOrder"})
    public void orderHistoryTest(){

        ProductCatalogs products = page.logins("mundejayshri123@gmail.com", "Pass1234");
      MyOrders orderss= products.varifyMyOrdersDisplay();
     Assert.assertTrue( orderss.myOrdersPage(productName));

    }
}
