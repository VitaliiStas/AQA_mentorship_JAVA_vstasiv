package org.Eleks.Gmail.po;


import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class MailSendPage extends BasePage {

    public String emailSubject = "";

    @FindBy(xpath = "//div[@class='T-I T-I-KE L3']")
    private static WebElement mailCreateButton;

    @FindBy(xpath = "//*[@class='vO' and @name='to']")
    private WebElement sendToEmail;

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement bodyOfMessage;

    @FindBy(xpath = "//input [@name='subjectbox' and @placeholder='Subject']")
    private WebElement subjectOfMessage;

    @FindBy(xpath = "//div[@class='dC']//*[@role='button']")
    private WebElement sendButton;

    @FindBy(xpath = "//table//tr[@role='row'][1]")
    private WebElement lastEmailFromTable;

    public static WebElement getMailCreateButtonForCheck() {
        return mailCreateButton;
    }

    public MailSendPage() {
    }

    public void goToEmailSendForm() {
        waitForElement(mailCreateButton, 10);
        mailCreateButton.click();
    }

    public String generateRandomString() {
        String randomString = RandomStringUtils.randomAlphabetic(12);
        return randomString;
    }

    @Step("Send test email")
    public void sendEmail(String testEmailText, String emailAddress) {
        goToEmailSendForm();
        waitForElement(sendToEmail, 10);
        sendToEmail.sendKeys(emailAddress);
        emailSubject = subjectOfMessage.getAttribute("aria-label") + " " + generateRandomString();
        subjectOfMessage.sendKeys(emailSubject);
        bodyOfMessage.sendKeys(testEmailText);
        sendButton.click();

    }

    @Step("Go to email page")
    public void goToEmailPage() {
        waitForElement(lastEmailFromTable, 10);
        lastEmailFromTable.click();
        new EmailPage();
    }


}

