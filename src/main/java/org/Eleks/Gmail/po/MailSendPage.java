package org.Eleks.Gmail.po;


import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class MailSendPage extends BasePage {

    @FindBy(xpath = "//div[@class='T-I T-I-KE L3']")
    private static WebElement mailCreateButton;

    @FindBy(xpath = "//*[@class='vO' and @name='to']")
    private WebElement sendToEmail;
    // todo виправити кирилицю
//    @FindBy(xpath = "//div[@aria-label='Тело письма']")
    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement bodyOfMessage;

    @FindBy(xpath = "//div[@class='dC']//*[@role='button']")
    private WebElement sendButton;

    @FindBy(xpath = "//table//tr[@role='row'][1]")
    private  WebElement lastEmailFromTable;

    public static WebElement getMailCreateButtonForCheck() {
        return mailCreateButton;
    }

    public MailSendPage() {
    }

    public void goToEmailSendForm() {
        waitForElement(mailCreateButton, 10);
        mailCreateButton.click();
    }
    @Step("Send test email")
    public void sendEmail(String testEmailText, String emailAddress) {
        goToEmailSendForm();
        sendToEmail.sendKeys(emailAddress);
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

