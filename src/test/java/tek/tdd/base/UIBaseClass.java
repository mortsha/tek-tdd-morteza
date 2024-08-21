package tek.tdd.base;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import tek.tdd.page.HomePage;
import tek.tdd.page.ProfilePage;
import tek.tdd.page.SignUpPage;
import tek.tdd.page.SingInPage;
import tek.tdd.utility.SeleniumUtility;

@Listeners({ExtentITestListenerClassAdapter.class})
public class UIBaseClass extends SeleniumUtility {

    private static final Logger LOGGER = LogManager.getLogger(UIBaseClass.class);

    public HomePage homePage;
    public SingInPage singInPage;
    public SignUpPage signUpPage;
    public ProfilePage profilePage;

    @BeforeMethod
    public void setupTest(){
        LOGGER.info("Setup Test and opening browser");
        setupBrowser();
        homePage = new HomePage();
        singInPage = new SingInPage();
        signUpPage = new SignUpPage();
        profilePage = new ProfilePage();
    }

    @AfterMethod
    public void testCleanup(ITestResult result){
        if(result.getStatus()== ITestResult.FAILURE){
            TakesScreenshot screenshot = (TakesScreenshot) getDriver();
            String shot =  screenshot.getScreenshotAs(OutputType.BASE64);

            ExtentTestManager.getTest().fail("Test failed Taking screen shot", MediaEntityBuilder.createScreenCaptureFromBase64String(shot).build());
        }
        LOGGER.info("Running after each tes and quite browser");
        quitBrowser();
    }
}
