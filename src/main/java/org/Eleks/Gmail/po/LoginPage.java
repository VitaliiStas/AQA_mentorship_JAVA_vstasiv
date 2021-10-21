package org.Eleks.Gmail.po;

import com.aventstack.extentreports.ExtentReports;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.Eleks.Gmail.bo.LoginBO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {


    @FindBy(xpath = "//*[@id='identifierId']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@type ='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//div[@jsname='B34EJ']")
    private WebElement incorrectEmailMessage;

    @FindBy(xpath = "//div[@jsname='B34EJ']/span[@jsslot]")
    private WebElement incorrectPasswordMessage;

    private static final Logger LOGGER = LogManager.getLogger(LoginBO.class);


    public LoginPage() {
        super();
    }

    private void inputData(WebElement field, String nameOrPassword) {
        waitForElement(field, 5);
        field.sendKeys(nameOrPassword);
        field.sendKeys(Keys.ENTER);
    }

    @Step("Type user name")
    public LoginPage typeUsername(String userName) {
        inputData(emailField, userName);
        return this;
    }

    @Step("Type user password")
    public LoginPage typePassword(String password) {
        inputData(passwordField, password);
        return this;
    }

    public HomePage login(String userName, String password) {
        typeUsername(userName);
        typePassword(password);
        return new HomePage();
    }

    @Step("Type false user name")
    public LoginPage typeFalseUsername(String userName) {
        typeUsername("Incorrect user name");
        waitForElement(incorrectEmailMessage, 5);
        checkErrorMessageIsDisplayed(incorrectEmailMessage);
        emailField.clear();
        inputData(emailField, userName);
        return this;
    }

    @Step("Type false password name")
    public LoginPage typeFalsePassword(String password) {
        typePassword("Incorrect password");
        waitForElement(incorrectPasswordMessage, 5);
        checkErrorMessageIsDisplayed(incorrectPasswordMessage);
        passwordField.clear();
//        inputData(passwordField, password);
        return this;
    }

    public HomePage loginFailed(String userName, String password) {
        typeFalseUsername(userName);
        typeFalsePassword(password);
        return new HomePage();
    }

}
