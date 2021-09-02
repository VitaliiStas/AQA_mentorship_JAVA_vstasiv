package org.Eleks.Gmail.listeners;

import io.qameta.allure.Attachment;
import org.Eleks.Gmail.factories.DriverFactory;
import org.Eleks.Gmail.po.BasePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BasePage implements ITestListener {

    @Attachment(value = "Page screenshot", type = "image/png")
    public  byte[] saveScreenshot() {
        return ((TakesScreenshot)DriverFactory.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Failed");
        saveScreenshot();

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("test pass");
    }
}
