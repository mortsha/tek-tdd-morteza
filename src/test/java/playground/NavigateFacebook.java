package playground;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class NavigateFacebook {

    private WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void NavigateToFacebook() {
       String applicationTitle = driver.getTitle();
        System.out.println(applicationTitle);
        Assert.assertEquals(applicationTitle,"Facebook – log in or sign up");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
