package org.Eleks.Gmail.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.log4testng.Logger;


public class DriverFactory  {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();//create webDriver which allow use webDriver in diff treads

// setUp the webDriver (use proper webDriver for test in this case chrome driver)

    public static WebDriver setUpWebDriver(Browsers properBrowser) {
        webDriver.set(properBrowser.create());
        return webDriver.get();
    }

    public static WebDriver getWebDriver() {
        return webDriver.get();// вик всюди
    }

    public enum Browsers {
        CHROME {
            public WebDriver create() {
                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--user-data-dir=c:\\Users\\vitalii.stasiv\\AppData\\Local\\Google\\Chrome\\User Data\\");
//                options.addArguments("--user-data-dir=C:\\Users\\vitalii.stasiv\\Desktop\\AQA_mentorship\\User Data\\");
//                options.addArguments("--incognito");
//                options.addArguments("start-maximized");
//                options.addArguments("enable-experimental-ui-automation");
//                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//                options.setExperimentalOption("useAutomationExtension", false);

                return new ChromeDriver(options);
            }
        },
        IE {
            public WebDriver create() {
                return new InternetExplorerDriver();
            }
        },
        FIREFOX {
            public WebDriver create() {
                return new FirefoxDriver();
            }
        };

        public WebDriver create() {
            return null;
        }
    }


}
