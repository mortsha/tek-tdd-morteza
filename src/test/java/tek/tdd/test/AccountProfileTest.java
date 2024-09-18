package tek.tdd.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.base.UIBaseClass;
import tek.tdd.utility.DataGenerator;

public class AccountProfileTest extends UIBaseClass {
    @Test(testName = "userStory5")
    public void updatePersonalInfo(){
        validCredentialSignIn();

        clickOnElement(homePage.accountLink);
        information("Click on account link");
        String expectedName = "David";
        sendText(profilePage.nameInput,expectedName);
        String expectedPhoneNumber = DataGenerator.getRandomPhoneNumber();
        sendText(profilePage.phoneNumberInput,expectedPhoneNumber);
        information("Sending name " + expectedName + " and phone number " + expectedPhoneNumber);

        clickOnElement(profilePage.updateButton);
        information("Clicking on update button");
        boolean isElementDisplayed =  isElementDisplayed(profilePage.toastMessage);
        Assert.assertTrue(isElementDisplayed);
        information("Toast message displayed");

        Assert.assertEquals(expectedName,getElementValue(profilePage.nameInput));
        Assert.assertEquals(expectedPhoneNumber,getElementValue(profilePage.phoneNumberInput));


    }
}
