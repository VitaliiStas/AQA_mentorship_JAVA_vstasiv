package org.Eleks.Gmail.wrappers.wrapper2;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CustomWebElementWraperImpl extends ElementImpl{
    protected WebElement webElement;

    public CustomWebElementWraperImpl(WebElement webElement) {
        super(webElement);
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


    public void sendKeysWrap(CharSequence... keysToSend) {
        System.out.println("Custom sendKeys1");
//        webElement.sendKeys(keysToSend);
    }
}
