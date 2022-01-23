package org.Eleks.Gmail.wrappers.wraper3;

import org.Eleks.Gmail.factories.DriverFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementRealisation extends Element {

    public ElementRealisation(WebElement webElement) {
        super(webElement);
    }

    public void setChecked(boolean value) {
        if (value != isChecked()) {
            webElement.click();
        }
    }

    public boolean isChecked() {
        return webElement.isSelected();
    }

    public void sendAndConfirmKeys(String keysToSend) {
        new WebDriverWait(DriverFactory.getWebDriver(), Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class, TimeoutException.class)
                .until(ExpectedConditions.visibilityOf(webElement));
        webElement.sendKeys(keysToSend);
        webElement.sendKeys(Keys.ENTER);
    }

}
