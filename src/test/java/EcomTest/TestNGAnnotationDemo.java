package EcomTest;

import org.testng.annotations.*;

public class TestNGAnnotationDemo {

    @BeforeSuite
    public void setupSuite() {
        System.out.println("BeforeSuite --- Setup DB connection");
    }

    @BeforeTest
    public void setupTest(){
        System.out.println("BeforeTest --- Setup test environment");
    }

    @BeforeClass
    public void beforeClassSetup() {
        System.out.println("BeforeClass --- Setup before any test method of this class runs");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod --- Run before each @Test method");
    }

    @Test(groups = "Smoke")
    public void testLogin(){
        System.out.println("Test1 --- Executing login test");
    }

    @Test
    public void testSearchProduct(){
        System.out.println("Test2 --- Executing product search test");
    }

    @Test(groups = "Smoke")
    public void testAddToCart(){
        System.out.println("Test3 --- Executing add to cart test");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod --- Run after each @Test method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("AfterClass --- Cleanup after all test methods of this class");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("AfterTest --- Cleanup after <test> execution");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite --- Close DB connection");
    }
}
