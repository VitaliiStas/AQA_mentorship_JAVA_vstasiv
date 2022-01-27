package org.Eleks.Gmail.bo;

import io.qameta.allure.Step;
import org.Eleks.Gmail.api.SendEmailByApi;
import org.Eleks.Gmail.factories.DriverFactory;
import org.Eleks.Gmail.utils.DateTimeHelper;
import org.Eleks.Gmail.po.EmailPage;
import org.Eleks.Gmail.po.MailSendPage;
import org.Eleks.Gmail.utils.FileUtils;
import org.Eleks.Gmail.utils.PropertyUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmailSendPageBO {

    private List<String> sendToOrCC;
    private String testEmailText;
    private String testEmailSubject;
    private String pathToFile;
    private String pathToDownloadedFile;

    public static final List<String> SEND_TO_LIST_OR_CC = Arrays.asList(
            "tt8397519@gmail.com"
            , "tt8397519+1@gmail.com"
            , "tt8397519+2@gmail.com"
            , "tt8397519+3@gmail.com");

    private final EmailPage emailPage = new EmailPage();
    private final MailSendPage mailSendPage = new MailSendPage();
    private final Actions action = new Actions(DriverFactory.getWebDriver());
    private final SendEmailByApi sendEmailByApi = new SendEmailByApi();
    private final FileUtils fileUtils = new FileUtils();

    private static final Logger LOGGER = LogManager.getLogger(EmailPage.class);

    private final String emailXpath = "//tr//td//span[@title]";

    public void sendAndCheckEmail() {
        mailSendPage
                .setExpectedUrl(PropertyUtils.getProperties("expectedUrlMailSendPage"));
        sendEmailWithSubjectEmailTextAttachFile(SEND_TO_LIST_OR_CC);
        mailSendPage
                .goToEmailPage(testEmailSubject);
        mailSendPage
                .verifyIsOpen();
        mailSendPage
                .clickDownloadButton();
        fileUtils.filesComparing(fileUtils.getAbsolutePath("src/main/resources/testImage.jpg")
                , "C:\\Users\\vitalii.stasiv\\Downloads\\testImage.jpg");
        checkEmail(testEmailText, testEmailSubject, SEND_TO_LIST_OR_CC);
    }



    public void sendAndCheckEmailWithBuilder() {
        mailSendPage.setExpectedUrl(PropertyUtils.getProperties("expectedUrlMailSendPage"));
        sendEmailWithBuilder();
        mailSendPage
                .goToEmailPage(testEmailSubject);
        mailSendPage
                .clickDownloadButton();
        fileUtils.filesComparing(pathToFile
                , pathToDownloadedFile);
        checkEmail(testEmailText
                , testEmailSubject
                , sendToOrCC);

    }
    public void sendAndCheck1000SizeEmail() {
        mailSendPage.setExpectedUrl(PropertyUtils.getProperties("expectedUrlMailSendPage"));
        sendEmailWith1000EmailText(sendToOrCC);
        mailSendPage
                .goToEmailPage(testEmailSubject);
        checkEmail(testEmailText
                , testEmailSubject
                , sendToOrCC);
    }

    //todo emty email
    public void sendAndCheckEmptyEmail() {
        mailSendPage.setExpectedUrl(PropertyUtils.getProperties("expectedUrlMailSendPage"));
        sendEmptyEmail(sendToOrCC);
        //check if email subject (no subject)
        Assert.assertEquals(
        new WebDriverWait(DriverFactory.getWebDriver()
                , Duration.ofSeconds(10))
                .until(ExpectedConditions
                .elementToBeClickable(By.xpath("//*[text()='(no subject)']//ancestor::tr")))
                .isDisplayed(),true,"Empty Email isn't presen't");
    }



    public void sendAndCheckEmailApi() {
        sendEmailApi();
    }

    private void sendEmailApi() {
        sendEmailByApi.sendEmailByApi(SEND_TO_LIST_OR_CC.get(1)
                , "API_" + testEmailSubject
                , "API_" + testEmailText);
    }

    public static void checkSortingEmailsOnEmailPage() {
        new EmailSendPageBO().checkEmailOrder();
    }

    public EmailSendPageBO() {
    }

    @Step("Check if email deleted")
    public void checkEmailDeleting() {
        int deleteNum = 4;
        DateTimeHelper dateTimeHelper = new DateTimeHelper();
        String deleteEmailTime = DateTimeHelper.getEmailDateTime(mailSendPage.getEmailForDelete(deleteNum));
        //type number email for deleting
        String latestEmailTime = dateTimeHelper.getLatestEmailTime();
        emailPage.deleteEmail(deleteNum);
        emailPage.checkIfEmailIsDeleted(latestEmailTime, deleteEmailTime);
    }

    @Step("Check email sending with builder")
    private void sendEmailWithBuilder() {
        mailSendPage
                .goToEmailSendForm();
        for (String emails : sendToOrCC) {
            mailSendPage
                    .typeSendTo(emails);
            mailSendPage
                    .typeSendTo(Keys.ENTER);
        }
        mailSendPage.typeEmailSubject(testEmailSubject);
        mailSendPage.typeEmailText(testEmailText);
        attachFile(pathToFile);
        mailSendPage.clickSendButton();
    }

    public void checkEmailDeletingWithSubject() {
        sendEmailWithBuilder();
        emailPage.deleteEmailBySubject(testEmailSubject);
        mailSendPage.checkIfEmailIsDeletedBySubject(testEmailSubject);
    }




    @Step("Check email sorting")
    private void checkEmailOrder() {
        ArrayList<LocalDateTime> defOrderEmailList = new ArrayList<>();
//        mailSendPage.waitForElement(DriverFactory.getWebDriver().findElement(By.xpath(emailXpath)), 10);
        List<WebElement> emailsList = DriverFactory
                .getWebDriver()
                .findElements(By.xpath(emailXpath));
        for (WebElement date : emailsList) {
            defOrderEmailList.add(DateTimeHelper.parseDateTime(date.getAttribute("title")));
        }
        ArrayList<LocalDateTime> sortedList = new ArrayList<>(defOrderEmailList);
        sortedList.sort(Collections.reverseOrder());
        Assert.assertEquals(sortedList, defOrderEmailList,
                "Email Sorting is incorrect");

    }





    @Step("Send test email with adding attached file, subject, email text,attachment")
    //send email with random body and subject and CC
    private void sendEmailWithSubjectEmailTextAttachFile(List<String> sendToOrCC) {
        openSendEmailAndInputRecipients(sendToOrCC);
        addEmailSubject(testEmailSubject);
        addEmailText(testEmailText);
        attachFile("src/main/resources/testImage.jpg");
        mailSendPage.clickSendButton();
    }


    private void sendEmailWith1000EmailText(List<String> sendToOrCC) {
        openSendEmailAndInputRecipients(sendToOrCC);
        addEmailSubject(testEmailSubject);
        addEmailText(testEmailText);
        mailSendPage.clickSendButton();
    }



@Step("Send empty email")
    private void sendEmptyEmail(List<String> sendToOrCC) {
        openSendEmailAndInputRecipients(sendToOrCC);
    mailSendPage.clickSendButton();
             DriverFactory.getWebDriver().switchTo().alert().accept();


    }

    private void openSendEmailAndInputRecipients(List<String> sendToOrCC){
        mailSendPage.goToEmailSendForm();
//        mailSendPage.waitForElement(mailSendPage.typeSendTo(), 10);
        for (String emails : sendToOrCC) {
            mailSendPage.typeSendTo(emails);
            mailSendPage.typeSendTo(Keys.ENTER);
        }
    }

    @Step("Add email subject")
    private void addEmailSubject(String testEmailSubject) {
        mailSendPage.typeEmailSubject(testEmailSubject);

    }

    @Step("Add email text")
    private void addEmailText(String testEmailText) {
        mailSendPage.typeEmailText(testEmailText);
    }

    @Step("Attach test file")
    private void attachFile(String relativePathToFile) {
        mailSendPage.typeAddFilePath(new FileUtils().getAbsolutePath(relativePathToFile));
    }


    private void checkCondition(String actualCondition, String expectedCondition, String failureMessage) {
        Assert.assertEquals(actualCondition, expectedCondition, failureMessage);
    }

    @Step("Check received email")
//    private void checkEmail(String expectedTestEmailText, String expectedSubjectForCheck, List<String> expectedListSentToEmails) {
//        checkCondition(testEmailText,expectedTestEmailText,"Received email BODY is incorrect");
//        checkCondition(testEmailSubject,expectedSubjectForCheck,"Received email subject is incorrect");
//        Assert.assertEquals(sendToOrCC,expectedListSentToEmails,"CC email is incorrect");
//        LOGGER.info("message is correct");
//    }
    private void checkEmail(String expectedTestEmailText, String expectedSubjectForCheck, List<String> expectedListSentToEmails) {
        checkCondition(testEmailSubject, getWebElementByText(expectedSubjectForCheck).getText(), "Received email subject is incorrect");
        checkCondition(testEmailText, getWebElementByText(expectedTestEmailText).getText(), "Received email BODY is incorrect");
        Assert.assertEquals(sendToOrCC, expectedListSentToEmails, "CC email is incorrect");
        LOGGER.info("message is correct");
    }

    private WebElement getWebElementByText(String text) {
        return new WebDriverWait(DriverFactory.getWebDriver(), Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class,
                TimeoutException.class).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[text()='" + text + "'])[last()]")));
//        return  DriverFactory.getWebDriver().findElement(By.xpath("//*[text()='" + text + "']"));
    }
//    private WebElement getSubjectByText (String text) {
//        return new WebDriverWait(DriverFactory.getWebDriver(), Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class,
//                TimeoutException.class).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='" + text + "']")));
////        return  DriverFactory.getWebDriver().findElement(By.xpath("//*[text()='" + text + "']"));
//    }


    private static String generateRandomString(int characterCount) {
        return RandomStringUtils.randomAlphabetic(characterCount);
    }

    private void checkEmailApi(String expectedTestEmailText, String expectedSubjectForCheck) {
        checkCondition(testEmailText
                , expectedTestEmailText
                , "Received email BODY is incorrect");
        checkCondition(testEmailSubject
                , expectedSubjectForCheck
                , "Received email subject is incorrect");
        LOGGER.info("message is correct!!");
    }


    public static EmailSendPageBO createFullEmail() {
        return new Builder()
                .setSendToOrCC(SEND_TO_LIST_OR_CC)
                .setPathToFile("src/main/resources/testImage.jpg")
//                .setPathToDownloadedFile("C:\\Users\\vitalii.stasiv\\Downloads\\testImage.jpg")
                .setPathToDownloadedFile("%USERPROFILE%\\Downloads\\testImage.jpg")
                .setTestEmailText("Body_builder " + generateRandomString(12))
                .setTestEmailSubject("Subject_builder " + generateRandomString(12))
                .build();
    }

    public static EmailSendPageBO create1000EmailText() {
        return new Builder()
                .setSendToOrCC(SEND_TO_LIST_OR_CC)
                .setTestEmailText("Body_builder " + generateRandomString(1000))
                .setTestEmailSubject("Subject_builder " + generateRandomString(12))
                .build();
    }

    public static EmailSendPageBO createEmptyEmail() {
        return new Builder()
                .setSendToOrCC(SEND_TO_LIST_OR_CC)
                .build();
    }

    //реалізація паттерну Builder

    static class Builder {
        private final EmailSendPageBO emailSendPageBO;

        //конструктор для обовязкових полів(поля які точно повинні бути ініціалізовані)
        public Builder() {
            emailSendPageBO = new EmailSendPageBO();
        }

        public Builder setTestEmailText(String testEmailText) {
            emailSendPageBO.testEmailText = testEmailText;
            return this;
        }

        public Builder setTestEmailSubject(String testEmailSubject) {
            emailSendPageBO.testEmailSubject = testEmailSubject;
            return this;
        }

        public Builder setSendToOrCC(List<String> sendToOrCC) {
            emailSendPageBO.sendToOrCC = sendToOrCC;
            return this;
        }

        public Builder setPathToFile(String pathToFile) {
            emailSendPageBO.pathToFile = pathToFile;
            return this;
        }

        public Builder setPathToDownloadedFile(String pathToDownloadedFile) {
            emailSendPageBO.pathToDownloadedFile = pathToDownloadedFile;
            return this;
        }

        public EmailSendPageBO build() {
            return emailSendPageBO;
        }
    }
}
