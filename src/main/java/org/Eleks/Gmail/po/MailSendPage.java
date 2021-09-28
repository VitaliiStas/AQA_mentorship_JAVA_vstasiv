package org.Eleks.Gmail.po;


import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class MailSendPage extends BasePage {
    //Page With email table/List
    public String emailSubject = "";
    public String emailBodyForCheck = "";
    private Integer emailNumForDelete = 0;
    private String emailXpath = "//tr//td//span[@title]";
    private String emailSubjectForDelete = "";
    private String subjectOnEmailPageXpath = "//span[@class='bog']/span";


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

    @FindBy(xpath = "//div[@role='button' and @data-tooltip='Delete']")
    private WebElement deleteEmailButton;

    @FindBy(xpath = "//tr//td//span[@title]")
    private WebElement emailDataElement;

    @FindBy(xpath = "//span[@class='bog']/span")
    private WebElement subjectOnEmailPage;


    public MailSendPage() {
    }

    public static WebElement getMailCreateButtonForCheck() {
        return mailCreateButton;
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
    //send email with random body and subject and CC
    public void sendEmail(String testEmailText, List<String> sendToOrCC) {
        goToEmailSendForm();
        waitForElement(sendToEmail, 10);
        for (String emails : sendToOrCC) {
            sendToEmail.sendKeys(emails);
            sendToEmail.sendKeys(Keys.ENTER);
        }
        emailSubject = subjectOfMessage.getAttribute("aria-label") + " " + generateRandomString();
        subjectOfMessage.sendKeys(emailSubject);
        //for verification of message body
        emailBodyForCheck = generateRandomString();
        bodyOfMessage.sendKeys(emailBodyForCheck);
        sendButton.click();
    }


//    @Step("Send test email")
//    //send email with random body and subject
//    public void sendEmail(String testEmailText, String emailAddress) {
//        goToEmailSendForm();
//        waitForElement(sendToEmail, 10);
//        sendToEmail.sendKeys(emailAddress);
//        emailSubject = subjectOfMessage.getAttribute("aria-label") + " " + generateRandomString();
//        subjectOfMessage.sendKeys(emailSubject);
//        //for verification of message body
//        emailBodyForCheck = generateRandomString();
//        bodyOfMessage.sendKeys(emailBodyForCheck);
//        sendButton.click();
//    }

    //send deff email
//    @Step("Send test email")
//    public void sendEmail(String testEmailText, String emailAddress) {
//        goToEmailSendForm();
//        waitForElement(sendToEmail, 10);
//        sendToEmail.sendKeys(emailAddress);
//        emailSubject = subjectOfMessage.getAttribute("aria-label") + " " + generateRandomString();
//        subjectOfMessage.sendKeys(emailSubject);
//        bodyOfMessage.sendKeys(testEmailText);
//        sendButton.click();
//    }

    @Step("Go to email page")
    public void goToEmailPage() {
        waitForElement(lastEmailFromTable, 10);
        lastEmailFromTable.click();
        new EmailPage();
    }


    @Step("Check email sorting")
//    public void checkEmailOrder() {
//        ArrayList<String> defOrderEmailList = new ArrayList<>();
//        waitForElement(emailDataElement, 10);
//        List<WebElement> emailsList = webDriver.findElements(emailDataXpath);
//        for (WebElement date : emailsList) {
//            defOrderEmailList.add(convertDate(date.getAttribute("title")));
//        }
//        ArrayList<String> sortedList = new ArrayList<>(defOrderEmailList);
//        Collections.sort(sortedList, Collections.reverseOrder());
//        Assert.assertEquals(sortedList, defOrderEmailList,
//                "Email Sorting is incorrect");
//    }
    public void checkEmailOrder() {
        ArrayList<LocalDateTime> defOrderEmailList = new ArrayList<>();
        waitForElement(emailDataElement, 10);
        List<WebElement> emailsList = webDriver.findElements(emailDataXpath);
        for (WebElement date : emailsList) {
            defOrderEmailList.add(parseDateTimeFromTitle(date.getAttribute("title")));
        }
        ArrayList<LocalDateTime> sortedList = new ArrayList<>(defOrderEmailList);
        Collections.sort(sortedList,
                Collections.reverseOrder());
        Assert.assertEquals(sortedList, defOrderEmailList,
                "Email Sorting is incorrect");
    }


    public String getEmailDateTime(WebElement element) {
        String emailDate = element.getAttribute("title");
        return emailDate;
    }

    //todo change to local date time
    public String convertDate(String date) {
        //take gmail date in format "EEEE, MMM d, yyyy, HH:mm a" to the "yyyy-MM-dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String convertedDate = sdf.format(new Date(date));
        return convertedDate;
    }

    //'Sun, Sep 26, 2021, 4:00 PM'
    // чому формат часу міняєтьсяб через Locale


    @Step("check If Email Is Deleted By Subject")
    public void checkIfEmailIsDeletedBySubject(String subjectForDeleting) {
        action.moveToElement(getWebElementByXpath("/html/body"));
        List<WebElement> subjectList = webDriver.findElements(By.xpath(subjectOnEmailPageXpath));
        List<String> subjectTextList = new ArrayList<>();
        waitForElement(emailDataElement, 10);

        for (WebElement element : subjectList) {
            if (element.getText().equals(emailSubjectForDelete)) {
                Assert.fail("Email deleting by SUBJECT failed!!!! \n email didn't DELETED or the email with the similar title is present");
            }
        }
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

    public void setEmailSubjectForDelete(String emailSubjectForDelete) {
        this.emailSubjectForDelete = emailSubjectForDelete;
    }

    public WebElement getEmailSubjectForDelete() {
        WebElement emailForDelete = null;
        List<WebElement> subjectList = webDriver.findElements(By.xpath(subjectOnEmailPageXpath));
        waitForElement(emailDataElement, 10);

        for (WebElement element : subjectList) {
            if (element.getText().equals(emailSubjectForDelete)) {
                emailForDelete = element;
            }
        }
        return emailForDelete;
    }

    @Step("Delete the selected email")
    public void deleteEmail() {
        action.contextClick(getEmailForDelete()).build().perform();
        waitForElement(deleteEmailButton, 10);
        action.moveToElement(deleteEmailButton).build().perform();
        deleteEmailButton.click();

    }

    @Step("Delete the selected email By Subject")
    public void deleteEmailBySubject() {
        action.contextClick(getEmailSubjectForDelete()).build().perform();
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

    protected LocalDateTime parseDateTimeFromTitle(String dateTimeFromTitle) {
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeFromTitle,
                DateTimeFormatter.ofPattern("E, MMM d, y, h:mm a", Locale.US));
        return dateTime;
    }

    private Actions action = new Actions(webDriver);
    private static final Logger LOGGER = LogManager.getLogger(EmailPage.class);

    private By emailDataXpath = By.xpath(emailXpath);

}

