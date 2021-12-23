package org.Eleks.Gmail.wrappers.wrapper2;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.testng.Assert;

import java.util.List;

public interface Element extends WebElement, WrapsElement, Locatable {
//define all default WebElement for using in the child class CustomWebElementWraper,

    void killAllHuman();

    void clickWrap();

    void submitWrap();

    void sendKeysWrap(CharSequence... keysToSend);

    void fillTheField(String text);

    void clickButton();

     @Override
     default void click() {

     }

     @Override
     default void submit() {

     }

     @Override
     default void sendKeys(CharSequence... keysToSend) {

     }

     @Override
     default void clear() {

     }

     @Override
     default String getTagName() {
          return null;
     }

     @Override
     default String getAttribute(String name) {
          return null;
     }

     @Override
     default boolean isSelected() {
          return false;
     }

     @Override
     default boolean isEnabled() {
          return false;
     }

     @Override
     default String getText() {
          return null;
     }

     @Override
     default List<WebElement> findElements(By by) {
          return null;
     }

     @Override
     default WebElement findElement(By by) {
          return null;
     }

     @Override
     default boolean isDisplayed() {
          return false;
     }

     @Override
     default Point getLocation() {
          return null;
     }

     @Override
     default Dimension getSize() {
          return null;
     }

     @Override
     default Rectangle getRect() {
          return null;
     }

     @Override
     default String getCssValue(String propertyName) {
          return null;
     }

     @Override
     default <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
          return null;
     }

     @Override
     default WebElement getWrappedElement() {
          return null;
     }

     @Override
     default Coordinates getCoordinates() {
          return null;
     }
}
