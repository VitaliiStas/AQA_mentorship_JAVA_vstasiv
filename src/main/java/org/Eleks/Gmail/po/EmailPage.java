package org.Eleks.Gmail.po;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class EmailPage extends MailSendPage {
    //Current Received Email Page
    @FindBy(xpath = "//*[@class='a3s aiL ']/div[1]")
    private WebElement receivedEmailBody;

    @FindBy(xpath = "//div[@class='ha']/h2")
    private WebElement receivedSubjectBody;

    private Actions action = new Actions(webDriver);

    private String sendToOrCCXpaths = "//tr//td//span[@class='gI']//span[@email]";

    private static final Logger LOGGER = LogManager.getLogger(EmailPage.class);

    public EmailPage() {
    }

    //    @Step("Check received email")
//    public void checkEmail(String testEmailText) {
//        waitForElement(receivedEmailBody, 10);
//        Assert.assertEquals(receivedEmailBody.getText(), testEmailText,
//                "Email body isn't correct");
//        waitForElement(receivedEmailBody, 10);
//    }

//Check received email with random Body and subject
//    @Step("Check received email")
//    public void checkEmail(String testEmailText, String subjectForCheck) {
//        waitForElement(receivedEmailBody, 10);
//        if (!receivedEmailBody.getText().equals(testEmailText)) {
//            LOGGER.warn("Received email body is incorrect");
//        } else if (!receivedSubjectBody.getText().equals(subjectForCheck)) ;
//        {
//            LOGGER.warn("Received email subject is incorrect");
//        }
//    }

    //Check received email with random Body, subject, emails
    @Step("Check received email")
    public void checkEmail(String testEmailText,String subjectForCheck,List<String> listSentToEmails) {
        waitForElement(receivedEmailBody, 10);

        if (!receivedEmailBody.getText().equals(testEmailText)||!receivedSubjectBody.getText().equals(subjectForCheck)) {
            LOGGER.warn("Received email body or subject is incorrect");
        } else if (!getListOfSendToOrCC().equals(subjectForCheck)) ;
        {
            LOGGER.info("Received emails is correct");
        }
    }


    protected List<String> getListOfSendToOrCC() {
        ArrayList<String> listOfSendToOrCC = new ArrayList<>();
        waitForElement(receivedSubjectBody, 10);
        List<WebElement> listOfSendToOrCCWebElements = webDriver.findElements(By.xpath(sendToOrCCXpaths));
        for (WebElement date : listOfSendToOrCCWebElements) {
            listOfSendToOrCC.add(date.getAttribute("email"));
        }
        return listOfSendToOrCC;
    }
}

