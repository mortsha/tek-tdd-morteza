package tek.tdd.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tek.tdd.base.UIBaseClass;

public class ProfilePage extends UIBaseClass {

    public ProfilePage(){
        PageFactory.initElements(getDriver(),this);
    }

    @FindBy(className = "account__information-email")
    public WebElement emailCreatedText;
}
