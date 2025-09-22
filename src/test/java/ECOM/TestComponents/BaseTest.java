package ECOM.TestComponents;

import PageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverInfo;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public LandingPage page;
    public WebDriver driver;

    public WebDriver initializeDriver() throws IOException {
        Properties property = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//ECOM//Resource//globalVariables.properties");
        property.load(file);
        String browserName = property.getProperty("browser");

        if (browserName.equalsIgnoreCase("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchBrowser() throws IOException {
        driver = initializeDriver();
        page = new LandingPage(driver);
        page.goTo();
        return page;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

    public List<HashMap<String, String>> getJsonProvider(String filePath) throws IOException {
        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        //string to hashmap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        return data;


    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Save into /reports
        String fullPath = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(fullPath));

        // link to it from index.html (which is in reports/)
        return testCaseName + ".png";
    }

}
