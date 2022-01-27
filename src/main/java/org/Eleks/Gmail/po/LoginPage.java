package org.Eleks.Gmail.po;

import io.qameta.allure.Step;
import org.Eleks.Gmail.bo.LoginBO;
import org.Eleks.Gmail.factories.UserFactory;
import org.Eleks.Gmail.models.User;
import org.Eleks.Gmail.wrappers.wraper3.ElementRealisation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {


    @FindBy(xpath = "//*[@id='identifierId']")
//    private WebElement emailField;
    private ElementRealisation emailField;

    @FindBy(xpath = "//input[@type ='password']")
//    private WebElement passwordField;
    private ElementRealisation passwordField;

    @FindBy(xpath = "//div[@jsname='B34EJ']")
//    private WebElement incorrectEmailMessage;
    private ElementRealisation incorrectEmailMessage;

    @FindBy(xpath = "//div[@jsname='B34EJ']/span[@jsslot]")
//    private WebElement incorrectPasswordMessage;
    private ElementRealisation incorrectPasswordMessage;

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
//        inputData(emailField, userName);
        emailField.sendAndConfirmKeys(userName);
        return this;
    }

    @Step("Type user password")
    public LoginPage typePassword(String password) {
//        inputData(passwordField, password);
        passwordField.sendAndConfirmKeys(password);
        return this;
    }

    public HomePage login(String userName, String password) {
        typeUsername(userName).typePassword(password);
        return new HomePage();

    }

//todo use data provider for the false test data
    @Step("Type false user name")
    public LoginPage typeFalseUsername(String userName) {
        typeUsername(userName);
        checkErrorMessageIsDisplayed(incorrectEmailMessage);
        emailField.clear();
        emailField.sendAndConfirmKeys(UserFactory.getUser().getUserName());
        return this;
    }

    @Step("Type false password name")
    public LoginPage typeFalsePassword(String password) {
        typePassword(password);
        checkErrorMessageIsDisplayed(incorrectPasswordMessage);
        return this;
    }

    public HomePage loginFailed(String userName, String password) {
        typeFalseUsername(userName);
        typeFalsePassword(password);

        return new HomePage();
    }

}
