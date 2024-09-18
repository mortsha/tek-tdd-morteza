package tek.tdd.test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.base.UIBaseClass;
import tek.tdd.utility.DataGenerator;
import java.util.List;

public class CreateAccountTest extends UIBaseClass {
    public void navigateToCreateNewAccountPage() {
        clickOnElement(homePage.singInLink);
        clickOnElement(singInPage.createNewAccountButton);
    }

    @Test(testName = "userStory4")
    public void createNewAccountTest() {
        navigateToCreateNewAccountPage();
        String name = "Steve";
        String expectedEmail = DataGenerator.emailGenerator(name);
        String password = "Password@123";
        signUpPage.fillUpCreateAccountForm(name, expectedEmail, password);

        String actualEmailText = getElementText(profilePage.emailCreatedText);
        Assert.assertEquals(actualEmailText, expectedEmail, "Email in profile page should match");
    }

    // user story 4.1
    @Test(testName = "userStory4.1")
    public void createNewAccountWithExistingEmail() {
        navigateToCreateNewAccountPage();
        String name = "Steve";
        String expectedEmail = "Mory123@gmail.com";
        String password = "Password@123";
        signUpPage.fillUpCreateAccountForm(name, expectedEmail, password);

        String actualErrorMessage = getElementText(signUpPage.errorMessage);
        Assert.assertEquals(actualErrorMessage, "this email is already exist, please use another email address", "Error Message should match");

    }

    @Test(testName = "userStory4.2")
    public void validateFieldErrors() {
        navigateToCreateNewAccountPage();
        clickOnElement(signUpPage.signUpButton);

        List<String> expectedError = List.of(
                "Name is a required field", "Email is a required field", "Password is a required field", "Confirm Password is a required field");

        List<WebElement> actualErrorElements = signUpPage.allErrorFieldsMessage;

        Assert.assertEquals(actualErrorElements.size(),expectedError.size());

        for (int i = 0; i < expectedError.size(); i++) {
            Assert.assertEquals(
                    getElementText(actualErrorElements.get(i)),expectedError.get(i)
            );
        }

    }
}
