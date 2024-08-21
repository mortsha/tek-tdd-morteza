package tek.tdd.utility;

import com.aventstack.extentreports.service.ExtentTestManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tek.tdd.base.BaseSetup;

import java.time.Duration;
import java.util.List;

public class SeleniumUtility extends BaseSetup {

    private static final Logger LOGGER = LogManager.getLogger(SeleniumUtility.class);

    private WebDriverWait getWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_TIME_IN_SECOND));
    }

    public WebElement waitToBeVisible(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitToBeVisible(WebElement element) {
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitToBeClickable(WebElement element) {
        return getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public String getElementText(By locator) {
        LOGGER.debug("Returning element text {} ", locator);
        return waitToBeVisible(locator).getText();
    }

    public String getElementText(WebElement element) {
        LOGGER.debug("Returning element text {} ", element);
        return waitToBeVisible(element).getText();
    }

    public boolean isElementEnabled(By locator) {
        LOGGER.debug("Checking element enable status {} ", locator);
        boolean isEnabled = waitToBeVisible(locator).isEnabled();
        LOGGER.debug("element is enabled status {} ", isEnabled);
        return isEnabled;
    }

    public boolean isElementEnabled(WebElement element) {
        LOGGER.debug("Checking element enable status {} ", element);
        boolean isEnabled = waitToBeVisible(element).isEnabled();
        LOGGER.debug("element is enabled status {} ", isEnabled);
        return isEnabled;
    }

    public void clickOnElement(WebElement element) {
        LOGGER.debug("Clicking on element {} ", element);
        waitToBeClickable(element).click();
    }

    public void sendText(WebElement element, String value) {
        LOGGER.debug("Sending text {} on element {}", value, element);
        WebElement targetElement = waitToBeVisible(element);
        targetElement.clear();
        targetElement.sendKeys(value);
    }

    public boolean isElementDisplayed(WebElement element) {
        LOGGER.debug("Checking element displayed status {} ", element);
        boolean isDisplayed = waitToBeVisible(element).isDisplayed();
        LOGGER.debug("element is displayed status {} ", isDisplayed);
        return isDisplayed;
    }

    public void information(String info) {
        ExtentTestManager.getTest().info(info);
    }
}
