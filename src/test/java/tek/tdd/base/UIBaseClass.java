package tek.tdd.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tek.tdd.page.HomePage;
import tek.tdd.page.SingInPage;
import tek.tdd.utility.SeleniumUtility;

public class UIBaseClass extends SeleniumUtility {

    private static final Logger LOGGER = LogManager.getLogger(UIBaseClass.class);

    public HomePage homePage;
    public SingInPage singInPage;

    @BeforeMethod
    public void setupTest(){
        LOGGER.info("Setup Test and opening browser");
        setupBrowser();
        homePage = new HomePage();
        singInPage = new SingInPage();
    }

    @AfterMethod
    public void testCleanup(){
        LOGGER.info("Running after each tes and quite browser");
        quitBrowser();
    }
}
