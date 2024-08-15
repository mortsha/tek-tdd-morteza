package tek.tdd.test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.base.UIBaseClass;

public class BasicSetupTest extends UIBaseClass {

    private void validateLogo(){
        String actualLogoTest = getElementText(By.className("top-nav__logo"));
        Assert.assertEquals(actualLogoTest,"TEKSCHOOL", "Logo text should match");
    }

    @Test(testName = "Validate Corner Logo")
    public void validateTopLeftCornetLogo(){
       validateLogo();
    }

    @Test(testName = "Validate logo with Sign in link")
    public void testingLogoWithSignInLink(){
       validateLogo();
        boolean isSignInLinkEnabled = isElementEnabled(By.id("signinLink"));
        Assert.assertTrue(isSignInLinkEnabled,"Sign in Link should be Enabled");

    }
}
