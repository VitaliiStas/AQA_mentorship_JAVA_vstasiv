package org.eleks.gmail.vstasiv;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.Eleks.Gmail.factories.DriverFactory;
import org.Eleks.Gmail.listeners.TestListener;
import org.Eleks.Gmail.utils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

//@Listeners({TestListener.class, AnnotationTransformer.class})
@Listeners(TestListener.class)
public class BaseTest  {
    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    @Step("Open browser and go to test URL")
    public void setUpTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = DriverFactory.setUpWebDriver(DriverFactory.Browsers.CHROME);
        LOGGER.info("Browser is opened",BaseTest.class);
        webDriver.manage().window().maximize();
        DriverFactory.getWebDriver().get(PropertyUtils.getProperties("testURL2"));
    }
    @AfterMethod
    @Step("Close the browser")
    public void quitTest() {
        DriverFactory.getWebDriver().quit();
        LOGGER.info("Browser is closed",BaseTest.class);
    }



}
