package org.Eleks.Gmail.wrappers.wrapper2;

import org.Eleks.Gmail.factories.DriverFactory;
import org.Eleks.Gmail.po.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

//implement all methods WebElement for using, include standard methods
public class ElementImpl implements Element {
    protected WebElement webElement;


    //
//
//todo use it
    public ElementImpl(WebElement webElement) {
        this.webElement = webElement;
    }


    public void clickButton() {
        wait(webElement, 10).click();
    }

    protected WebElement wait(WebElement element, int waitTime) {
        return new WebDriverWait(DriverFactory.getWebDriver(), Duration.ofSeconds(waitTime))
                .ignoring(StaleElementReferenceException.class, TimeoutException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public void waitWrap(WebElement element, int waitTime) {

    }

    public void killAllHuman() {

        Assert.assertFalse(webElement.isDisplayed(), "IT's alive!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!@_@");

    }

    public void clickWrap() {
//        webElement.click();
        System.out.println("Custom click1");
    }


    public void submitWrap() {
//        webElement.submit();
        System.out.println("Custom submit1");
    }

    //todo ??????????? implycity wait dosent work properly
    public void sendAndConfirmKeys(String keysToSend) {
        new WebDriverWait(DriverFactory.getWebDriver(), Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class, TimeoutException.class)
                .until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(keysToSend);
        webElement.sendKeys(Keys.ENTER);
    }


    @Override
    public void click() {
        webElement.click();
    }

    @Override
    public void submit() {
        webElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        webElement.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        webElement.clear();
    }

    @Override
    public String getTagName() {
        return webElement.getTagName();
    }

    @Override
    public String getAttribute(String name) {

        return webElement.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return webElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return webElement.isDisplayed();
    }

    @Override
    public String getText() {
        return webElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return webElement.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return webElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return webElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return webElement.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return webElement.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }

    @Override
    public WebElement getWrappedElement() {
        return webElement;
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable) webElement).getCoordinates();
    }
}
