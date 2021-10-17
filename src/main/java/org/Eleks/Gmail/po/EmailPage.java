package org.Eleks.Gmail.po;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class EmailPage extends MailSendPage {
    //Current Received Email Page
    @FindBy(xpath = "//*[@class='a3s aiL ']/div[1]")
    private WebElement receivedEmailBody;

    @FindBy(xpath = "//div[@class='ha']/h2")
    private WebElement receivedSubjectBody;

    @FindBy(xpath = "//div[@role='button' and @aria-label='Show details']")
    private WebElement showDetails;

    @FindBy(xpath = "//div[@class='iw ajw']")
    private WebElement frameCC;


    private final String sendToOrCCXpaths = "//span[@translate ]/span[@email and @class='g2']";

    private static final Logger LOGGER = LogManager.getLogger(EmailPage.class);



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
    public void checkEmail(String testEmailText, String actualSubjectForCheck, List<String> listSentToEmails) {
//        pauseSec(2);
        waitForElement(receivedEmailBody, 10);
        Assert.assertEquals(receivedEmailBody.getText(),testEmailText,"Received email BODY is incorrect");
        Assert.assertEquals(actualSubjectForCheck,receivedSubjectBody.getText(),"Received email subject is incorrect");
        Assert.assertEquals(getListOfSendToOrCC(),listSentToEmails,"CC email is incorrect");
        LOGGER.info("message is correct");

    }

    protected List<String> getListOfSendToOrCC() {
        waitForElement(receivedSubjectBody, 10);
        ArrayList<String> listOfSendToOrCC = new ArrayList<>();
        List<WebElement> listOfSendToOrCCWebElements = webDriver.findElements(By.xpath(sendToOrCCXpaths));
        for (WebElement cc : listOfSendToOrCCWebElements) {
            listOfSendToOrCC.add(cc.getAttribute("email"));
        }
        return listOfSendToOrCC;
    }
}

