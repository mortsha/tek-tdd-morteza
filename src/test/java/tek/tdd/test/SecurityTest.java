package tek.tdd.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.base.UIBaseClass;

public class SecurityTest extends UIBaseClass {


    @Test(testName = "UserStory1-PositiveSignIn")
    public void testSignInCredentials(){
    clickOnElement(homePage.singInLink);
    sendText(singInPage.emailInput,"mory123@gmail.com");
    sendText(singInPage.passwordInput,"Mory@123");
    clickOnElement(singInPage.loginButton);

    boolean isAccountEnabled = isElementDisplayed(homePage.accountLink);
        Assert.assertTrue(isAccountEnabled,"Looking for account link to be displayed after login");

    }

}
