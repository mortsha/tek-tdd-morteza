package tek.tdd.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.base.UIBaseClass;
import tek.tdd.utility.DataGenerator;

public class CreateAccountTest extends UIBaseClass {

    @Test
    public void createNewAccountTest(){
        clickOnElement(homePage.singInLink);
        clickOnElement(singInPage.createNewAccountButton);
        String name = "Steve";
        String expectedEmail = DataGenerator.emailGenerator(name);
        String password = "Password@123";
        signUpPage.fillUpCreateAccountForm(name,expectedEmail,password);

        String actualEmailText= getElementText(profilePage.emailCreatedText);
        Assert.assertEquals(actualEmailText,expectedEmail,"Email in profile page should match");
    }

    @Test
    public void createNewAccountWithExistingEmail(){
        clickOnElement(homePage.singInLink);
        clickOnElement(singInPage.createNewAccountButton);
        String name = "Steve";
        String expectedEmail = "Mory123@gmail.com";
        String password = "Password@123";
        signUpPage.fillUpCreateAccountForm(name,expectedEmail,password);

        String actualErrorMessage = getElementText(signUpPage.errorMessage);
        Assert.assertEquals(actualErrorMessage,"this email is already exist, please use another email address","Error Message should match");

    }
}
