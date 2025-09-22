package ECOM.Resource;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {

    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Test Report");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Jayshri");
        return extent;

    }
}
