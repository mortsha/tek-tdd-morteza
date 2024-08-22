package tek.tdd.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.base.UIBaseClass;

public class BasicSetupTest extends UIBaseClass {

    private void validateLogo(){
        String actualLogoTest = getElementText(homePage.topLeftLogo);
        Assert.assertEquals(actualLogoTest,"TEKSCHOOL", "Logo text should match");
    }

    @Test(testName = "Validate Corner Logo")
    public void validateTopLeftCornetLogo(){
       validateLogo();
    }

    @Test(testName = "Validate logo with Sign in link")
    public void testingLogoWithSignInLink(){
       validateLogo();
        boolean isSignInLinkEnabled = isElementEnabled(homePage.singInLink);
        Assert.assertTrue(isSignInLinkEnabled,"Sign in Link should be Enabled");

    }
}
