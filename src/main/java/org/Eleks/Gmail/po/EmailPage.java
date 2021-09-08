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
import java.util.Collections;
import java.util.List;

public class EmailPage extends MailSendPage {
    @FindBy(xpath = "//*[@class='a3s aiL ']/div[1]")
    private WebElement receivedEmailBody;
    @FindBy(xpath = "//*[@role='toolbar']/li[@data-tooltip='Delete']")
    private WebElement deleteEmailButton;

    private By emailDate = By.xpath("//td//span[@title]");
    WebElement emailForDelete = webDriver.findElement(emailDate);

    private Actions action = new Actions(webDriver);
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

    @Step("Check email sorting")
    public void checkEmailOrder() {
//        waitForElement(webDriver.findElement(emailDate), 5);
        ArrayList<String> defOrderEmailList = new ArrayList<>();
        List<WebElement> emailsList = webDriver.findElements(emailDate);
        for (WebElement date : emailsList) {
            defOrderEmailList.add(date.getAttribute("title").substring(5, 11));

        }
        ArrayList<String> sortedList = new ArrayList<>(defOrderEmailList);
        Collections.sort(sortedList, Collections.reverseOrder());
        Assert.assertEquals(sortedList, (defOrderEmailList));
    }


    public String emailDate(WebElement element) {
        String emailDate = element.getAttribute("title");
//        return dateEmailForDelete;
        return emailDate;
    }

    @Step("Check if email is deleted")
    public void checkIfEmailIsDeleted(String latestEmailTime, String deleteEmailTime) {
        Assert.assertNotEquals(latestEmailTime, deleteEmailTime,
                "The latest email is not deleted");

    }

    @Step("Delete the latest email")
    public void deleteEmail() {
        action.moveToElement(emailForDelete).build().perform();
        deleteEmailButton.click();
        action.moveToElement(webDriver.findElement(By.xpath("/html/body"))).build().perform();
        waitForElement(webDriver.findElement(emailDate),10);

    }

    public String getDeleteEmailTime() {
        String deleteEmailTime = emailDate(emailForDelete);
        return deleteEmailTime;
    }
    public String getLatestEmailTime() {
        String latestEmailTime = emailDate(webDriver.findElement(emailDate));
        return latestEmailTime;
    }
}

