package EcomTest;

import ECOM.TestComponents.BaseTest;
import PageObjects.Checkout;
import PageObjects.MyOrders;
import PageObjects.PlaceOrder;
import PageObjects.ProductCatalogs;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrder extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData")
    public void orderConfirmed(HashMap<String ,String> input) throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";

        ProductCatalogs products = page.logins(input.get("email"),input.get("password") );
        List<WebElement> productsList = products.getProductList();
        Checkout checkouts = products.addToCart(input.get("product"));
        Boolean match = checkouts.checkCart(input.get("product"));
        Assert.assertTrue(match);
        PlaceOrder placeorders = checkouts.checkoutProduct();
        placeorders.selectCountry("India");
        placeorders.placeOrder();
        String confirmMessage = placeorders.confirmMessage();
        System.out.println(confirmMessage);
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
    }


    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonProvider(System.getProperty("user.dir") +"//src//test//java//ECOM//Data//Data.json");
        return new Object[][]{{data.get(0)}};
    }

    @Test(dataProvider = "getData")
    public void orderHistoryTest(HashMap<String ,String> input){

        ProductCatalogs products = page.logins(input.get("email"),input.get("password"));
        MyOrders orderss= products.varifyMyOrdersDisplay();
        Assert.assertTrue( orderss.myOrdersPage(input.get("product")));

    }



}
