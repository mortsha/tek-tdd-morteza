package playground;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyFirstTest {

    @BeforeMethod
    public void runBeforeTest(){
        System.out.println("this is before each test");
    }

    @Test
    public void myFirstTestMethod(){
        System.out.println("Hello world");
    }


    @AfterMethod
    public void runAfterEachTest(){
        System.out.println("This is running after each test");
    }
}
