package org.Eleks.Gmail.bo;

import io.qameta.allure.Step;
import org.Eleks.Gmail.factories.DriverFactory;
import org.Eleks.Gmail.factories.UserFactory;
import org.Eleks.Gmail.po.DateTimeHelper;
import org.Eleks.Gmail.po.EmailPage;
import org.Eleks.Gmail.po.MailSendPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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



    protected static List<String> sendToListOrCC = Arrays.asList(
            "tt8397519@gmail.com"
            , "tt8397519+1@gmail.com"
            , "tt8397519+2@gmail.com"
            , "tt8397519+3@gmail.com");

    private final EmailPage emailPage = new EmailPage();
    private final MailSendPage mailSendPage = new MailSendPage();
    private final Actions action = new Actions(DriverFactory.getWebDriver());
    


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


    public void sendAndCheckEmail() {
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



    public  void sendAndCheckEmailWithBuilder() {
//        EmailSendPageBO emailSendPageBO = new EmailSendPageBO();
        mailSendPage.setExpectedUrl(UserFactory.getProperties("expectedUrlMailSendPage"));
        sendEmailWithBuilder();
        mailSendPage
                .goToEmailPage();
        mailSendPage
                .downloadFile();
        filesComparing(getPathToFile()
                , getPathToDownloadedFile());
        emailPage.checkEmail(getTestEmailText()
                , getTestEmailSubject()
                , getSendToOrCC());

    }

    public static void checkSortingEmailsOnEmailPage() {
        new EmailSendPageBO().checkEmailOrder();
    }

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
    @Step("Check if email deleted")
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
    @Step("Check email sending with builder")
    private void sendEmailWithBuilder() {
        mailSendPage
                .goToEmailSendForm();
        mailSendPage
                .waitForElement(mailSendPage
                        .getSendToEmail(), 10);
        for (String emails : sendToOrCC) {
            mailSendPage
                    .getSendToEmail()
                    .sendKeys(emails);
            mailSendPage
                    .getSendToEmail()
                    .sendKeys(Keys.ENTER);
        }
        mailSendPage.emailSubject = getTestEmailSubject();
        mailSendPage
                .getSubjectOfMessage()
                .sendKeys(mailSendPage.getEmailSubject());
        mailSendPage.getBodyOfMessage()
                .sendKeys(getTestEmailText());
        mailSendPage
                .attachFile(pathToFile);
        mailSendPage
                .getSendButton()
                .click();
    }

    public void checkEmailDeletingWithSubject() {
        EmailPage emailPage = new EmailPage();
        sendEmailWithBuilder();
        emailPage.setEmailSubjectForDeleteText(getTestEmailSubject());
        emailPage.deleteEmailBySubject();
        new EmailSendPageBO()
                .checkIfEmailIsDeletedBySubject();
    }

    private  void filesComparing(String pathToFile1, String pathToFile2) {
        File expectedFile = new File(MailSendPage.getPathToFile(pathToFile1));
        File actualFile = new File(MailSendPage.getPathToFile(pathToFile2));
        try {
            Assert.assertEquals(expectedFile.createNewFile(), actualFile.createNewFile(), "The files are different");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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

    public static EmailSendPageBO create() {
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
