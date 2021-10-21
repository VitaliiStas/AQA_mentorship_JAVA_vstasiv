package org.Eleks.Gmail.po;


import io.qameta.allure.Step;
import org.Eleks.Gmail.factories.DriverFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class MailSendPage extends BasePage {
    //Page With email table/List
    public String emailSubject = "";
    private final Actions action = new Actions(webDriver);
    private String emailBodyForCheck = "";
    private Integer emailNumForDelete = 0;
    private String emailSubjectForDeleteText = "";
    private final String emailXpath = "//tr//td//span[@title]";
    private final String subjectOnEmailPageXpath = "//span[@class='bog']/span";

    private final By emailDataXpath = By.xpath(emailXpath);


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

    @FindBy(xpath = "//input[@type='file']")
    private WebElement addFile;

    @FindBy(xpath = "//div[@role='button' and @data-tooltip='Download']")
    private WebElement downloadFile;

    public MailSendPage() {
    }

    public WebElement getBodyOfMessage() {
        return bodyOfMessage;
    }

    public WebElement getSendButton() {
        return sendButton;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public WebElement getSubjectOfMessage() {
        return subjectOfMessage;
    }

    public String getEmailBodyForCheck() {
        return emailBodyForCheck;
    }

    public WebElement getSendToEmail() {
        return sendToEmail;
    }

    public String getEmailSubjectForDeleteText() {
        return emailSubjectForDeleteText;
    }

    public String getSubjectOnEmailPageXpath() {
        return subjectOnEmailPageXpath;
    }

    public By getEmailDataXpath() {
        return emailDataXpath;
    }

    public WebElement getEmailDataElement() {
        return emailDataElement;
    }

    public static WebElement getMailCreateButtonForCheck() {
        return mailCreateButton;
    }

    public static String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(12);
    }

    public String getAbsolutePath(String relativePath) {
        return FileSystems.getDefault().getPath(relativePath).normalize().toAbsolutePath().toString();
    }

    public void setEmailBodyForCheck(String emailBodyForCheck) {
        this.emailBodyForCheck = emailBodyForCheck;
    }

    public void setEmailSubjectForDeleteText(String emailSubjectForDeleteText) {
        this.emailSubjectForDeleteText = emailSubjectForDeleteText;
    }

    public void setEmailNumForDelete(Integer emailNumForDelete) {
        this.emailNumForDelete = emailNumForDelete;
    }

    public void downloadFile() {
        waitForElement(downloadFile, 10);
        downloadFile.click();
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
        pauseSec(1);
        waitForElement(lastEmailFromTable, 10);
        lastEmailFromTable.click();
        new EmailPage();
    }


    //    @Step("Check email sorting")
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

    public static String getPathToFile(String pathToFile) {
        return String.valueOf(Paths.get(pathToFile));
    }


    @Step("check If Email Is Deleted By Subject")
    public void checkIfEmailIsDeletedBySubject() {

//        setEmailSubjectForDelete("nclXsapqDkno");
        action.moveToElement(getWebElementByXpath("/html/body"));
        List<WebElement> subjectList = webDriver.findElements(By.xpath(subjectOnEmailPageXpath));
        List<String> subjectTextList = new ArrayList<>();
        waitForElement(emailDataElement, 10);

        for (WebElement element : subjectList) {
            if (element.getText().equals(getEmailSubjectElement().getText())) {
                Assert.fail("Email deleting by SUBJECT failed!!!! \n email didn't DELETED or the email with the similar title is present");
            }
        }
    }

    @Step("Check if email is deleted")
    public void checkIfEmailIsDeleted(String latestEmailTime, String deleteEmailTime) {
        Assert.assertNotEquals(latestEmailTime, deleteEmailTime,
                "The latest email is not deleted");
    }


    @Step("Attach test file")
    public void attachFile(String relativePathToFile) {
//        attach test file
//        waitForElement(addFile, 10);
        addFile.sendKeys(getAbsolutePath(relativePathToFile));
    }

    public void goToEmailSendForm() {
        waitForElement(mailCreateButton, 10);
        mailCreateButton.click();
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
        action.contextClick(getEmailSubjectElement())
                .build()
                .perform();
        waitForElement(deleteEmailButton, 10);
        action.moveToElement(deleteEmailButton)
                .build()
                .perform();
        deleteEmailButton.click();

    }

    int index;

    public WebElement getEmailSubjectElement() {
//        todo wait until область видимості index?????????????????????????
        List<WebElement> subjectList = new WebDriverWait(DriverFactory.getWebDriver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.xpath(getSubjectOnEmailPageXpath()))));
        for (WebElement element : subjectList) {
            if (element.getText().equals(emailSubjectForDeleteText)) {
                index = subjectList.indexOf(element);
            }
        }
        return subjectList.get(index);
    }

    protected Integer getEmailNumForDelete() {
        return emailNumForDelete;
    }

    protected WebElement getEmailForDelete() {
        //use the selected email num for delete proper email
        String xpathForEmailDeleting = String.valueOf(new StringBuffer("//tr//td//span[@title]").insert(4,
                String.format("[%s]", Integer.toString(getEmailNumForDelete()))));
        return getWebElementByXpath("//tr[3]//td//span[@title]");
    }


}

