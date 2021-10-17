package org.Eleks.Gmail.bo;

import io.qameta.allure.Step;
import org.Eleks.Gmail.factories.DriverFactory;
import org.Eleks.Gmail.factories.UserFactory;
import org.Eleks.Gmail.po.BasePage;
import org.Eleks.Gmail.po.DateTimeHelper;
import org.Eleks.Gmail.po.EmailPage;
import org.Eleks.Gmail.po.MailSendPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.*;
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

    public String getTestEmailText() {
        return testEmailText;
    }

    public String getTestEmailSubject() {
        return testEmailSubject;
    }

    public List<String> getSendToOrCC() {
        return sendToOrCC;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public String getPathToDownloadedFile() {
        return pathToDownloadedFile;
    }

    protected static List<String> sendToListOrCC = Arrays.asList(
            "tt8397519@gmail.com"
            , "tt8397519+1@gmail.com"
            , "tt8397519+2@gmail.com"
            , "tt8397519+3@gmail.com");

    private final EmailPage emailPage = new EmailPage();
    private final MailSendPage mailSendPage = new MailSendPage();
    private final Actions action = new Actions(DriverFactory.getWebDriver());


    // TODO: 15.10.2021
    //неможе бути статік бо буде спільний для всіх тестів і перешкоджатиме паралельному запуску тестів
    private static final ThreadLocal<EmailSendPageBO> FOR_TEST = new ThreadLocal<>();

    public static EmailSendPageBO getForTest() {
          return FOR_TEST.get();
    }


    public static void sendAndCheckEmail() {
        EmailSendPageBO test = new EmailSendPageBO();
        EmailPage emailPage = new EmailPage();
        test.mailSendPage
                .setExpectedUrl(UserFactory.getProperties("expectedUrlMailSendPage"));
        test.sendEmail(sendToListOrCC);
        test.mailSendPage
                .goToEmailPage();
        test.mailSendPage
                .verifyIsOpen();
        test.mailSendPage
                .downloadFile();
        filesComparing(test.mailSendPage
                        .getAbsolutePath("src/main/resources/testImage.jpg")
                , "C:\\Users\\vitalii.stasiv\\Downloads\\testImage.jpg");
        emailPage.checkEmail(test.mailSendPage
                        .getEmailBodyForCheck(), test.mailSendPage.emailSubject
                , sendToListOrCC);
    }

    private void sendEmailWithBuilder() {

        getForTest().mailSendPage
                .goToEmailSendForm();
        getForTest().mailSendPage
                .waitForElement(getForTest().mailSendPage
                        .getSendToEmail(), 10);
        for (String emails : getForTest().sendToOrCC) {
            getForTest().mailSendPage
                    .getSendToEmail()
                    .sendKeys(emails);
            getForTest().mailSendPage
                    .getSendToEmail()
                    .sendKeys(Keys.ENTER);
        }
        getForTest().mailSendPage.emailSubject = getForTest().getTestEmailSubject();
        getForTest().mailSendPage
                .getSubjectOfMessage()
                .sendKeys(getForTest().mailSendPage.getEmailSubject());
        getForTest().mailSendPage.getBodyOfMessage()
                .sendKeys(getForTest().getTestEmailText());
        getForTest().mailSendPage
                .attachFile(getForTest().pathToFile);
        getForTest().mailSendPage
                .getSendButton()
                .click();
    }

    public static void sendAndCheckEmailWithBuilder() {
        FOR_TEST.set(createAnObjectForTest());
        EmailSendPageBO emailSendPageBO = new EmailSendPageBO();
        emailSendPageBO.getForTest().mailSendPage.setExpectedUrl(UserFactory.getProperties("expectedUrlMailSendPage"));
        emailSendPageBO.getForTest().sendEmailWithBuilder();
        emailSendPageBO.getForTest().mailSendPage
                .goToEmailPage();
        emailSendPageBO.getForTest().mailSendPage
                .downloadFile();
        filesComparing(emailSendPageBO.getForTest().getPathToFile()
                , emailSendPageBO.getForTest().getPathToDownloadedFile());
        emailSendPageBO.getForTest().emailPage.checkEmail(emailSendPageBO.getForTest().getTestEmailText()
                , emailSendPageBO.getForTest().getTestEmailSubject()
                , emailSendPageBO.getForTest().getSendToOrCC());

    }

    public static void checkSortingEmailsOnEmailPage() {
        new EmailSendPageBO().checkEmailOrder();
    }

    //todo винести в  ВО
    @Step("check If Email Is Deleted By Subject")
    public void checkIfEmailIsDeletedBySubject() {
        action.moveToElement(mailSendPage.getWebElementByXpath("/html/body"));
        List<WebElement> subjectList = DriverFactory.getWebDriver()
                .findElements(By.xpath(new MailSendPage()
                        .getSubjectOnEmailPageXpath()));
        mailSendPage
                .waitForElement(new MailSendPage()
                        .getEmailDataElement(), 10);

        for (WebElement element : subjectList) {
            if (element.getText().equals(new MailSendPage().getEmailSubjectForDeleteText())) {
                Assert.fail("Email deleting by SUBJECT failed!!!! \n email didn't DELETED or the email with the similar title is present");
            }
        }
    }

    public EmailSendPageBO() {

    }

    public static void checkEmailDeleting() {
        EmailPage emailPage = new EmailPage();
        DateTimeHelper dateTimeHelper = new DateTimeHelper();
        String deleteEmailTime = dateTimeHelper.getDeleteEmailTime();
        //type number email for deleting
        emailPage.setEmailNumForDelete(4);
        emailPage.deleteEmail();
        String latestEmailTime = dateTimeHelper.getLatestEmailTime();
        emailPage.checkIfEmailIsDeleted(latestEmailTime, deleteEmailTime);
    }

    private void checkEmailDeletingWithSubject() {
        EmailPage emailPage = new EmailPage();
//        Subject QxwTszekQsTj
//        Subject jgKrCweDWWdt
//        Subject HLPjHjEWoLwk
//        Subject lhxdzVWmBtaK
//        Subject DnqVbSYpUjAR
//        Subject XmmeXVrJWZRt

        emailPage.setEmailSubjectForDelete(getForTest().getTestEmailSubject());
        emailPage.deleteEmailBySubject();
        new EmailSendPageBO()
                .checkIfEmailIsDeletedBySubject();
    }

    private static void filesComparing(String pathToFile1, String pathToFile2) {
        File expectedFile = new File(MailSendPage.getPathToFile(pathToFile1));
        File actualFile = new File(MailSendPage.getPathToFile(pathToFile2));
        try {
            Assert.assertEquals(expectedFile.createNewFile(), actualFile.createNewFile(), "The files are different");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //todo винести в  ВО
    @Step("Check email sorting")
    private void checkEmailOrder() {
        ArrayList<LocalDateTime> defOrderEmailList = new ArrayList<>();
        mailSendPage.waitForElement(new MailSendPage().getEmailDataElement(), 10);
        List<WebElement> emailsList = DriverFactory
                .getWebDriver()
                .findElements(new MailSendPage().getEmailDataXpath());
        for (WebElement date : emailsList) {
            defOrderEmailList.add(DateTimeHelper.parseDateTime(date.getAttribute("title")));
        }
        ArrayList<LocalDateTime> sortedList = new ArrayList<>(defOrderEmailList);
        sortedList.sort(Collections.reverseOrder());
        Assert.assertEquals(sortedList, defOrderEmailList,
                "Email Sorting is incorrect");

    }

    //todo винести в  ВО
    @Step("Send test email with attachment")
    //send email with random body and subject and CC
    private void sendEmail(List<String> sendToOrCC) {
        mailSendPage.goToEmailSendForm();
        mailSendPage.waitForElement(mailSendPage.getSendToEmail(), 10);
        for (String emails : sendToOrCC) {
            mailSendPage.getSendToEmail().sendKeys(emails);
            mailSendPage.getSendToEmail().sendKeys(Keys.ENTER);
        }
        mailSendPage.emailSubject = mailSendPage.getSubjectOfMessage()
                .getAttribute("aria-label") + " " + MailSendPage.generateRandomString();
        mailSendPage.getSubjectOfMessage()
                .sendKeys(mailSendPage.getEmailSubject());
        //for verification of message body
        mailSendPage.setEmailBodyForCheck(MailSendPage.generateRandomString());
        mailSendPage.getBodyOfMessage().sendKeys(mailSendPage.getEmailBodyForCheck());
//        mailSendPage.getBodyOfMessage().sendKeys(testEmailText);
        mailSendPage.attachFile("src/main/resources/testImage.jpg");
        mailSendPage.getSendButton().click();
    }

    private static EmailSendPageBO createAnObjectForTest() {
        return new Builder()
                .setSendToOrCC(sendToListOrCC)
                .setPathToFile("src/main/resources/testImage.jpg")
                .setPathToDownloadedFile("C:\\Users\\vitalii.stasiv\\Downloads\\testImage.jpg")
                .setTestEmailText("Body_builder" + MailSendPage.generateRandomString())
                .setTestEmailSubject("Subject_builder " + MailSendPage.generateRandomString())
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
