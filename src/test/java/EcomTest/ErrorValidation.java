package EcomTest;

import ECOM.TestComponents.BaseTest;
import PageObjects.Checkout;
import PageObjects.PlaceOrder;
import PageObjects.ProductCatalogs;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidation extends BaseTest {
    @Test(groups = {"ErrorHandling"})
    public void NegativeLoginCase() throws IOException, InterruptedException {
       page.logins("mundejayshri3@gmail.com", "Pass123e4");
       Assert.assertEquals("Incorrect email or.",page.errorMessage());

    }
}
