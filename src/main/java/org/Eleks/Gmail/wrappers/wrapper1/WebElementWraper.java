package org.Eleks.Gmail.wrappers.wrapper1;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.testng.Assert;

import java.util.List;

public class WebElementWraper implements WebElementWraperIn {
    protected WebElement webElement;
//    protected WebDriver webDriver;
//
//
////todo use it
//    public WebElementWraper(WebElement webElement) {
//        this.webElement = webElement;
//    }
//
//    public void fillTheField(String text) {
//        wait(webElement, 10).sendKeys(text);
//    }
//
//    public void clickSendButton() {
//        wait(webElement, 10).click();
//    }
//
//    private WebElement wait(WebElement element, int waitTime) {
//        return new WebDriverWait(webDriver, Duration.ofSeconds(waitTime))
//                .ignoring(StaleElementReferenceException.class, TimeoutException.class)
//                .until(ExpectedConditions.visibilityOf(element));
//    }
// todo fixing
    public WebElementWraper( WebElement webElement) {
        this.webElement = webElement;
    }


    public void killAllHuman() {
        Assert.assertFalse(webElement.isDisplayed(), "IT's alive!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!@_@");

    }
    //todo fix
    @Override
    public void click() {
        Assert.assertFalse(webElement.isDisplayed(), "IT's alive!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!@_@");
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
        throw new UnsupportedOperationException("getScreenshotAs() not yet implemented");
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
