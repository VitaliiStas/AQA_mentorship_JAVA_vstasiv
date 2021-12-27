package org.Eleks.Gmail.wrappers.wrapper2;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.testng.Assert;

import java.util.List;

public interface Element extends WebElement, WrapsElement, Locatable {
//define all custom methods WebElement for using

    void waitWrap(WebElement element, int waitTime);

    void killAllHuman();

    void clickWrap();

    void submitWrap();

    void sendAndConfirmKeys(String keysToSend);

    void clickButton();

}
