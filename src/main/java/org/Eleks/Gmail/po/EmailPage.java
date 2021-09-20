package org.Eleks.Gmail.po;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EmailPage extends MailSendPage {
    private String emailXpath = "//tr//td//span[@title]";
    private Integer emailNumForDelete = 0;

    @FindBy(xpath = "//*[@class='a3s aiL ']/div[1]")
    private WebElement receivedEmailBody;
    //    @FindBy(xpath = "//*[@role='toolbar']/li[@data-tooltip='Delete']")
    @FindBy(xpath = "//div[@role='button' and @data-tooltip='Delete']")
    private WebElement deleteEmailButton;
    @FindBy(xpath = "//tr//td//span[@title]")
    private WebElement emailDataElement;

    private By emailDataXpath = By.xpath(emailXpath);


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
        ArrayList<String> defOrderEmailList = new ArrayList<>();
        waitForElement(emailDataElement, 10);
        List<WebElement> emailsList = webDriver.findElements(emailDataXpath);
        for (WebElement date : emailsList) {
            defOrderEmailList.add(convertDate(date.getAttribute("title")));
        }
        ArrayList<String> sortedList = new ArrayList<>(defOrderEmailList);
        Collections.sort(sortedList, Collections.reverseOrder());
        Assert.assertEquals(sortedList, defOrderEmailList,
                "Email Sorting is incorrect");
    }

    public String getEmailDateTime(WebElement element) {
        String emailDate = element.getAttribute("title");
        return emailDate;
    }


    public String convertDate(String date) {
        //take gmail date in format "EEEE, MMM d, yyyy, HH:mm a" to the "yyyy-MM-dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String convertedDate = sdf.format(new Date(date));
        return convertedDate;
    }

    @Step("Check if email is deleted")
    public void checkIfEmailIsDeleted(String latestEmailTime, String deleteEmailTime) {
        Assert.assertNotEquals(latestEmailTime, deleteEmailTime,
                "The latest email is not deleted");

    }

    public void setEmailNumForDelete(Integer emailNumForDelete) {
        this.emailNumForDelete = emailNumForDelete;
    }

    public Integer getEmailNumForDelete() {
        return emailNumForDelete;
    }

    public WebElement getEmailForDelete() {
        //use the selected email num for delete proper email
        String xpathForEmailDeleting = String.valueOf(new StringBuffer("//tr//td//span[@title]").insert(4, String.format("[%s]", Integer.toString(getEmailNumForDelete()))));
        WebElement emailForDelete = getWebElementByXpath("//tr[3]//td//span[@title]");
        return emailForDelete;
    }

    @Step("Delete the latest email")
    public void deleteEmail() {

        action.contextClick(getEmailForDelete()).build().perform();
        waitForElement(deleteEmailButton, 10);
        action.moveToElement(deleteEmailButton).build().perform();
        deleteEmailButton.click();


    }

    public String getDeleteEmailTime() {
        String deleteEmailTime = getEmailDateTime(getEmailForDelete());
        return deleteEmailTime;
    }

    public String getLatestEmailTime() {
        String latestEmailTime = getEmailDateTime(emailDataElement);
        return latestEmailTime;
    }
}

