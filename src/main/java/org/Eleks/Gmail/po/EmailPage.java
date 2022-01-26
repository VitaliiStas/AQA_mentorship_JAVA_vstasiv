package org.Eleks.Gmail.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;


public class EmailPage extends MailSendPage {
    //Current Received Email Page

    @FindBy(xpath = "//div[@role='button' and @aria-label='Show details']")
    private WebElement showDetails;

    @FindBy(xpath = "//div[@class='iw ajw']")
    private WebElement frameCC;


    private static final String SEND_TO_OR_CC_XPATHS = "//span[@translate ]/span[@email and @class='g2']";

//    @FindBy(xpath = "//*[@class='a3s aiL ']/div[1]")
//    private WebElement receivedEmailBody;
    @FindBy(xpath = "//div[@class='ha']/h2")
    private WebElement receivedSubjectBody;

    public List<String> getListOfSendToOrCC() {
        waitForElement(receivedSubjectBody, 10);
        ArrayList<String> listOfSendToOrCC = new ArrayList<>();
        List<WebElement> listOfSendToOrCCWebElements = webDriver.findElements(By.xpath(SEND_TO_OR_CC_XPATHS));
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

