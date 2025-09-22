package TestPractice;

import org.testng.annotations.Test;

public class ParameterDemo {

    @Test(groups = "Smoke")
    public void parameterTest(){
        System.out.println("Group Parameter");
    }

    @Test
    public void WOTparameterTest(){
        System.out.println("without Group Parameter");
    }
}
