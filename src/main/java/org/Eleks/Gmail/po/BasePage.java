package org.Eleks.Gmail.po;


import io.qameta.allure.Step;
import org.Eleks.Gmail.factories.DriverFactory;
import org.Eleks.Gmail.listeners.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class BasePage {
    protected WebDriver webDriver;
    protected String expectedUrl;

    private static final Logger LOGGER = LogManager.getLogger(BasePage.class);

    @FindBy(xpath = "//*[@id='gbwa']/div/a")
    private static WebElement menu;

    @FindBy(xpath = "//a[contains(@href,'https://mail.google.com/mail/')]")
    private static WebElement mailIcon;

    @FindBy(xpath = "//iframe[contains(@src,'https://ogs.google.com/u/0/')]")
    private static WebElement mailIconFrame;

    @FindBy(xpath = "//iframe[contains(@src,'https://hangouts.google.com/webchat/u')]")
    private static WebElement sideBarFrame;

    @FindBy(xpath = "//*/button/figure/img")
    private static WebElement profileImageForCheck;

    public static WebElement getProfileImageForCheck() {
        return profileImageForCheck;
    }

    public static WebElement getMenu() {
        return menu;
    }

    public static WebElement getMailIcon() {
        return mailIcon;
    }

    public static WebElement getMailIconFrame() {
        return mailIconFrame;
    }

    public static WebElement getSideBarFrame() {
        return sideBarFrame;
    }


    public BasePage() {
        this.webDriver = DriverFactory.getWebDriver();
        PageFactory.initElements(this.webDriver, this);
//        this.getClass()
    }

    public WebElement getWebElementByXpath(String elementXpath) {
        waitForElement(webDriver.findElement(By.xpath("/html/body")), 10);
        return webDriver.findElement(By.xpath(elementXpath));
    }


    public void waitForElement(WebElement webElement, Integer timeForWaitInSec) {
//        new WebDriverWait(webDriver, timeForWaitInSec).until(ExpectedConditions.visibilityOf(webElement));
        new WebDriverWait(webDriver, Duration.ofSeconds(timeForWaitInSec)).ignoring(StaleElementReferenceException.class,
                TimeoutException.class).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    //For pause
    public static void pauseSec(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setExpectedUrl(String expectedUrlHomePage) {
        this.expectedUrl = expectedUrlHomePage;
    }

    public String getExpectedUrl() {
        return expectedUrl;
    }

    private void checkUrl() {
//            pauseSec(2);//We need to pause before check for the page page loading
        waitForElement(menu, 10);
        Assert.assertTrue(webDriver.getCurrentUrl().startsWith(getExpectedUrl()),
                "<<<<<<<<<<Web page URL mismatch(incorrect URL for the " + getClass() + ")>>>>>>>>>>>>");

    }

    private void checkElementOnPage() {
        //if "menu" button is present on the current - that's true
        if (!(menu.isDisplayed())) {
            LOGGER.warn("!!!!!!!Element unavailable on the page: " + getClass());
        } else {
            LOGGER.warn("Element is present on the page: " + getClass());
        }
    }

    private void checkElementOnPage(WebElement element) {
        //if "menu" button is present on the current - that's true
        if (!(element.isDisplayed())) {
            LOGGER.warn("!!!!!!!Element unavailable on the page: " + getClass());
        } else {
            LOGGER.info("Element is present on the page: " + getClass());
        }
    }

    public void verifyIsOpen() {
        checkUrl();
        checkElementOnPage();
    }

    @Step("Check if correct page is opened")
    public void verifyIsOpen(WebElement elementForCheck) {
        checkUrl();
        checkElementOnPage(elementForCheck);
        LOGGER.info("Correct page for class: " + getClass() + " opened");
    }

    public void clickOnElement(WebElement element) {
        waitForElement(element, 2);
//        pauseSec(5);
        element.click();
    }

    public void clickOnElement(WebElement element, String attribute, String value) {
        //use if need to check if element is available if not do it available by clicking on it
        waitForElement(element, 2);
        if (!element.getAttribute(attribute).startsWith(value)) {
            element.click();
        }
    }

    public void goToServicesMenu(WebElement servicesMenuFrame) {
        //navigate to the gmail services menu which located in the frame
        webDriver.switchTo().frame(servicesMenuFrame);
    }

    public void goToSideBarMenu() {
        //navigate to the gmail sidebar
        webDriver.switchTo().frame(getSideBarFrame());
//        getSideBarFrame().click();
    }

    public void switchToTab(Integer tabNum) {
        pauseSec(2);
        ArrayList<String> openedTabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(openedTabs.get(tabNum));
    }

    @Step("Check if error message is displayed on the page")
    public void checkErrorMessageIsDisplayed(WebElement element) {
//        Assert.assertNotNull(element,"Error message: " +element.getText() + " is absent on the: "+this.webDriver.getTitle() + " page");
        if (!element.isDisplayed()) {
            LOGGER.warn("Error message is not displayed : " + element.getText());
            TestListener testListener = new TestListener();
            testListener.saveScreenshot();
        } else if (element.isDisplayed())
            LOGGER.info("Proper massage is displayed : " + element.getText());
    }

}

