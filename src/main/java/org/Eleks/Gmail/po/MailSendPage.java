package org.Eleks.Gmail.po;


import io.qameta.allure.Step;
import org.Eleks.Gmail.bo.EmailSendPageBO;
import org.Eleks.Gmail.factories.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class MailSendPage extends BasePage {
    private final Actions action = new Actions(webDriver);


    private final String subjectOnEmailPageXpath = "//span[@class='bog']/span[@class='bqe']";


    @FindBy(xpath = "//div[@class='T-I T-I-KE L3']")
    private  WebElement mailCreateButton;

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

    @FindBy(xpath = "//input[@type='file']")
    private WebElement addFile;

    @FindBy(xpath = "//div[@role='button' and @data-tooltip='Download']")
    private WebElement downloadFile;

    public MailSendPage() {
    }

    public void typeEmailText(String emailText) {
        wait(bodyOfMessage, 10).sendKeys(emailText);
    }

    public void typeEmailSubject(String subject) {
        wait(subjectOfMessage, 10).sendKeys(subject);
    }

    public void typeSendTo(String sendTo) {
        wait(sendToEmail, 10).sendKeys(sendTo);
    }

    public void typeSendTo(Keys keys) {
        wait(sendToEmail, 10).sendKeys(keys);
    }


    public void clickSendButton() {
        wait(sendButton, 10).click();
    }

    private WebElement wait(WebElement element, int waitTime) {
        return new WebDriverWait(webDriver, Duration.ofSeconds(waitTime))
                .ignoring(StaleElementReferenceException.class, TimeoutException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }


    public String getTimeOfEmailElement() {
        return DateTimeHelper.getEmailDateTime(emailDataElement);
    }


    public void clickDownloadButton() {
        new WebDriverWait(webDriver, Duration.ofSeconds(20))
                .ignoring(StaleElementReferenceException.class,
                        TimeoutException.class)
                .until(ExpectedConditions.visibilityOf(downloadFile)).click();
    }

//    @Step("Go to email page")
//    public EmailPage goToEmailPage() {
//        pauseSec(2);
//        new WebDriverWait(webDriver, Duration.ofSeconds(20))
//                .until(ExpectedConditions.elementToBeClickable(lastEmailFromTable)).click();
////        //table//tr[@role='row'][1]
//        System.out.println("Element was clicked");
//        return new EmailPage();
//    }
    @Step("Go to email page")
    public EmailPage goToEmailPage(String text) {
//        pauseSec(2);
        new WebDriverWait(webDriver, Duration.ofSeconds(10)).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//*[text()='"+text+"']//ancestor::tr"))).click();
        System.out.println("Element was clicked");
        return new EmailPage();
    }





    @Step("check If Email Is Deleted By Subject")
    public void checkIfEmailIsDeletedBySubject(String subject) {
        Assert.assertTrue((new WebDriverWait(DriverFactory.getWebDriver(),
                        Duration.ofSeconds(10))
                        .until(ExpectedConditions
                                .invisibilityOfElementLocated(By.xpath("//*[@class='bog']/*[text()='" + subject + "']")))),
                "Email deleting by SUBJECT failed!!!! " +
                        "email didn't DELETED or the email with the similar title is present");
    }

    @Step("Check if email is deleted")
    public void checkIfEmailIsDeleted(String latestEmailTime, String deleteEmailTime) {
        Assert.assertNotEquals(latestEmailTime, deleteEmailTime,
                "The latest email is not deleted");
    }


    public void typeAddFilePath(String path) {
         addFile.sendKeys(path);
    }

    public void goToEmailSendForm() {
        wait(mailCreateButton, 10).click();
    }


    @Step("Delete the selected email")
    public void deleteEmail(int num) {
        action.contextClick(getEmailForDelete(num)).build().perform();
        waitForElement(deleteEmailButton, 10);
        action.moveToElement(deleteEmailButton).build().perform();
        deleteEmailButton.click();

    }

    @Step("Delete the selected email By Subject")
    public void deleteEmailBySubject(String subject) {
        action.contextClick(getEmailSubjectElement(subject))
                .build()
                .perform();
        waitForElement(deleteEmailButton, 10);
        action.moveToElement(deleteEmailButton)
                .build()
                .perform();
        deleteEmailButton.click();

    }

    private WebElement getEmailSubjectElement(String subject) {
        return new WebDriverWait(DriverFactory.getWebDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//*[@class='bog']/*[text()='" + subject + "']")));
    }
    protected WebElement getEmailForDelete(int emailNumForDelete) {
        //use the selected email num for delete proper email
        return new WebDriverWait(webDriver,Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(String.valueOf(new StringBuffer("//tr//td//span[@title]")
                        .insert(4, String.format("[%s]", emailNumForDelete))))));
    }


}

