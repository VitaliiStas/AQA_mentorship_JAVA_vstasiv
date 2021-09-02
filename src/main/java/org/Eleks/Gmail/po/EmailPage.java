package org.Eleks.Gmail.po;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class EmailPage extends MailSendPage {
    @FindBy(xpath = "//*[@class='a3s aiL ']/div[1]")
    private WebElement receivedEmailBody;


    private static final Logger LOGGER = LogManager.getLogger(EmailPage.class);

    public EmailPage() {
    }
    @Step("Check received email")
    public void checkEmail(String testEmailText) {
        waitForElement(receivedEmailBody, 10);
        Assert.assertEquals(receivedEmailBody.getText(), testEmailText,
                "Email body isn't correct");
        waitForElement(receivedEmailBody, 10);
    }
}
