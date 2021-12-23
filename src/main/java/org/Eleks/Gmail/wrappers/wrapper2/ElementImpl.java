package org.Eleks.Gmail.wrappers.wrapper2;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

//define all default WebElement for using in the child class CustomWebElementWraperImpl, also a couple custom methods
public class ElementImpl implements Element {
    protected WebElement webElement;
    protected WebDriver webDriver;

    //
//
//todo use it
    public ElementImpl(WebElement webElement) {
        this.webElement = webElement;
    }

    public void fillTheField(String text) {
        wait(webElement, 10).sendKeys(text);
    }

    public void clickButton() {
        wait(webElement, 10).click();
    }

    protected WebElement wait(WebElement element, int waitTime) {
        return new WebDriverWait(webDriver, Duration.ofSeconds(waitTime))
                .ignoring(StaleElementReferenceException.class, TimeoutException.class)
                .until(ExpectedConditions.visibilityOf(element));
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

    }

    @Override
    public String getTagName() {
        return null;
    }

    @Override
    public String getAttribute(String name) {
        return null;
    }

    @Override
    public boolean isSelected() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return null;
    }

    @Override
    public WebElement findElement(By by) {
        return null;
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public Dimension getSize() {
        return null;
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public String getCssValue(String propertyName) {
        return null;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }

    @Override
    public WebElement getWrappedElement() {
        return null;
    }

    @Override
    public Coordinates getCoordinates() {
        return null;
    }
}
