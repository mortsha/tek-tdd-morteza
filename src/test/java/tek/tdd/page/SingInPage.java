package tek.tdd.page;

import com.aventstack.extentreports.service.ExtentTestManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tek.tdd.utility.SeleniumUtility;

public class SingInPage extends SeleniumUtility {

    public SingInPage(){
        PageFactory.initElements(getDriver(),this);
    }

    @FindBy(id = "email")
    public WebElement emailInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "loginBtn")
    public WebElement loginButton;

    @FindBy(className = "error")
    public WebElement errorMessage;

    @FindBy(id="newAccountBtn")
    public WebElement createNewAccountButton;

    public void doSignIn(String email, String password){
        ExtentTestManager.getTest().info("Sign in with" + email + " And "+ password);
        sendText(emailInput,email);
        sendText(passwordInput,password);
        clickOnElement(loginButton);
    }

}
