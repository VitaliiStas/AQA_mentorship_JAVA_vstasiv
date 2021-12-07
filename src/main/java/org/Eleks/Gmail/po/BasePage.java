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

import java.nio.file.FileSystems;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class BasePage {
    protected WebDriver webDriver;
    protected String expectedUrl;

    private static final Logger LOGGER = LogManager.getLogger(BasePage.class);

    @FindBy(xpath = "//*[@id='gbwa']/div/a")
    private  WebElement menu;

    @FindBy(xpath = "//a[contains(@href,'https://mail.google.com/mail/')]")
    private  WebElement mailIcon;

    @FindBy(xpath = "//iframe[contains(@src,'https://ogs.google.com/u/0/')]")
    private  WebElement mailIconFrame;

    @FindBy(xpath = "//iframe[contains(@src,'https://hangouts.google.com/webchat/u')]")
    private  WebElement sideBarFrame;

    @FindBy(xpath = "//*/button/figure/img")
    private  WebElement profileImageForCheck;




    public  void clickOnMailIcon() {
        clickOnElement(mailIcon);
    }
//
//    public static WebElement getMailIconFrame() {
//        return mailIconFrame;
//    }



    public BasePage() {
        this.webDriver = DriverFactory.getWebDriver();
        PageFactory.initElements(this.webDriver, this);
    }

    public void waitForElement(WebElement webElement, Integer timeForWaitInSec) {
        new WebDriverWait(webDriver, Duration.ofSeconds(timeForWaitInSec)).ignoring(StaleElementReferenceException.class,
                TimeoutException.class).until(ExpectedConditions.visibilityOf(webElement));
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


    @Step("Check if the 'MENU' present on the page")
    private void checkElementOnPage() {
        //if "menu" button is present on the current - that's true
        Assert.assertTrue(menu.isDisplayed(),"!!!!!!!'MENU' unavailable on the page: " + getClass());
            }

//todo assert fail
private void checkIfTheSelectedElementIsPresent(WebElement element) {
        //if "menu" button is present on the current - that's true
    Assert.assertTrue(element.isDisplayed(),"!!!!!!!'Element:'"+element+" unavailable on the page: " + getClass());
    }



    //todo не інкапсульовано напизати метод veryfy is open
    @Step("Check if correct page is opened")
    public void verifyIsOpen() {
        checkUrl();
        checkElementOnPage();
        LOGGER.info("Correct page for class: " + getClass() + " opened");
    }
    @Step("Check if profile image isDisplayed")
    public void checkIfProfileImageIsPresent() {
        checkIfTheSelectedElementIsPresent(profileImageForCheck);
    }


//    private void verifyIsOpen(WebElement elementForCheck) {
//        checkUrl();
//        checkIfTheSelectedElementIsPresent(elementForCheck);
//        LOGGER.info("Correct page for class: " + getClass() + " opened");
//    }

    public void clickOnElement(WebElement element) {
        waitForElement(element, 2);
        element.click();
    }

    public void clickOnElement(WebElement element, String attribute, String value) {
        //use if need to check if element is available if not do it available by clicking on it
        waitForElement(element, 2);
        if (!element.getAttribute(attribute).startsWith(value)) {
            element.click();
        }
    }

    public void goToServicesMenu() {
        //navigate to the gmail services menu which located in the frame
        webDriver.switchTo().frame(mailIconFrame);
    }

    public void switchToTab(Integer tabNum) {
        ArrayList<String> openedTabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(openedTabs.get(tabNum));
    }

    @Step("Check if error message is displayed on the page")
    public void checkErrorMessageIsDisplayed(WebElement element) {
        Assert.assertTrue(element.isDisplayed(),"Error message is not displayed : " + element.getText());
    }

}

