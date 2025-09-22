package ExtendReportDemo;

import ECOM.TestComponents.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExtendReportDemo extends BaseTest {

    ExtentReports extent;

    @BeforeTest
    public void config() {
        String path = System.getProperty("user.dir")+"//reports//index.html";
        System.out.println(path);
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Test Report");

         extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Jayshri");


    }
    @Test
    public void test(){
        extent.createTest("Demo");
        WebDriverManager.chromedriver().setup();
       WebDriver driver = new ChromeDriver();
       driver.get("https://rahulshettyacademy.com/");
        System.out.println(driver.getTitle());
        driver.close();
        extent.flush();
    }
}
