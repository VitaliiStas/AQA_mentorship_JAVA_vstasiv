package org.Eleks.Gmail.po;

import io.qameta.allure.Step;
import org.Eleks.Gmail.bo.EmailSendPageBO;
import org.Eleks.Gmail.factories.DriverFactory;
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

    public WebElement getReceivedSubjectBody() {
        return receivedSubjectBody;
    }
    public WebElement getReceivedEmailBody() {
        return receivedEmailBody;
    }





    public List<String> getListOfSendToOrCC() {
        waitForElement(getReceivedSubjectBody(), 10);
        ArrayList<String> listOfSendToOrCC = new ArrayList<>();
        List<WebElement> listOfSendToOrCCWebElements = webDriver.findElements(By.xpath(sendToOrCCXpaths));
        for (WebElement cc : listOfSendToOrCCWebElements) {
            listOfSendToOrCC.add(cc.getAttribute("email"));
                    }
        return listOfSendToOrCC;
    }

    public WebElement findElementByText(String textForSearch){
        pauseSec(2);

        return webDriver
                .findElement(By.xpath("//*[contains(text(), '"+ textForSearch + "')]"));
    }
}

