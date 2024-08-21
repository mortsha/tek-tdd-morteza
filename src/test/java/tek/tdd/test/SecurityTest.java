package tek.tdd.test;

import com.aventstack.extentreports.service.ExtentTestManager;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.base.UIBaseClass;

public class SecurityTest extends UIBaseClass {

    // user story 1
    @Test(testName = "UserStory1-PositiveSignIn")
    public void testSignInCredentials() {
        clickOnElement(homePage.singInLink);

        information("Sign in with credentials");

        singInPage.doSignIn("mory123@gmail.com", "Mory@123");

        boolean isAccountEnabled = isElementDisplayed(homePage.accountLink);
        Assert.assertTrue(isAccountEnabled, "Looking for account link to be displayed after login");

    }

    // activity
    // user story 2 and 3

    @Test(dataProvider = "InvalidTestData")
    public void negativeSignInTest(String email, String password) {
        clickOnElement(homePage.singInLink);
        singInPage.doSignIn(email, password);

        String actualErrorMessage = getElementText(singInPage.errorMessage);
        Assert.assertEquals(actualErrorMessage, "wrong username or password", "Error Message should match");

    }

    @DataProvider(name = "InvalidTestData")
    private String[][] invalidTestData() {
        return new String[][]{
                {"Wrong222@gmail.com", "Password@123"},
                {"Mory123@gmail.com", "WrongPassword"},
                {"Wrong222@gmail.com", "WrongPassword"},
        };
    }

}
