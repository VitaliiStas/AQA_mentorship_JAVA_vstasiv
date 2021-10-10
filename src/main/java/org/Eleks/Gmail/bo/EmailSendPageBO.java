package org.Eleks.Gmail.bo;

import io.qameta.allure.Step;
import org.Eleks.Gmail.factories.DriverFactory;
import org.Eleks.Gmail.factories.UserFactory;
import org.Eleks.Gmail.po.BasePage;
import org.Eleks.Gmail.po.DateTimeHelper;
import org.Eleks.Gmail.po.EmailPage;
import org.Eleks.Gmail.po.MailSendPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class EmailSendPageBO {
    private static final Logger LOGGER = LogManager.getLogger(EmailPage.class);
    private final Actions action = new Actions(DriverFactory.getWebDriver());
    MailSendPage mailSendPage = new MailSendPage();
    protected static List<String> sendToListOrCC = Arrays.asList(
            "tt8397519+1@gmail.com", "tt8397519+2@gmail.com", "tt8397519+3@gmail.com");

    //todo винести в  ВО
    @Step("Send test email with attachment")
    //send email with random body and subject and CC
    public void sendEmail(String testEmailText, List<String> sendToOrCC, String pathToFile) {
        mailSendPage.goToEmailSendForm();
        mailSendPage.waitForElement(mailSendPage.getSendToEmail(), 10);
        for (String emails : sendToOrCC) {
            mailSendPage.getSendToEmail().sendKeys(emails);
            mailSendPage.getSendToEmail().sendKeys(Keys.ENTER);
        }
        mailSendPage.emailSubject = mailSendPage.getSubjectOfMessage()
                .getAttribute("aria-label") + " " + mailSendPage.generateRandomString();
        mailSendPage.getSubjectOfMessage()
                .sendKeys(mailSendPage.getEmailSubject());
        //for verification of message body
        mailSendPage.setEmailBodyForCheck(mailSendPage.generateRandomString());
//        mailSendPage.getBodyOfMessage().sendKeys(new MailSendPage().getEmailBodyForCheck());
        mailSendPage.getBodyOfMessage().sendKeys(testEmailText);
        mailSendPage.attachFile(pathToFile);
        mailSendPage.getSendButton().click();
    }


    public static void sendAndCheckEmail() {

        EmailPage emailPage = new EmailPage();
        MailSendPage mailSendPage = new MailSendPage();
        mailSendPage.setExpectedUrl(UserFactory.getProperties("expectedUrlMailSendPage"));

        new EmailSendPageBO().sendEmail(mailSendPage.generateRandomString(),
                sendToListOrCC, "src/main/resources/testImage.jpg");
        mailSendPage.goToEmailPage();
        mailSendPage.verifyIsOpen(MailSendPage.getMailCreateButtonForCheck());
        mailSendPage.downloadFile();
        filesComparing(mailSendPage.getAbsolutePath("src/main/resources/testImage.jpg")
                , "C:\\Users\\vitalii.stasiv\\Downloads\\testImage.jpg");
        emailPage.checkEmail(mailSendPage.getEmailBodyForCheck(), mailSendPage.emailSubject, sendToListOrCC);

    }
//    public static void sendAndCheckEmail() {
//
////        MailSendPage mailSendPage = new MailSendPage();
//        EmailPage emailPage = new EmailPage();
//        MailSendPage mailSendPage = new MailSendPage();
//        mailSendPage.setExpectedUrl(UserFactory.getProperties("expectedUrlMailSendPage"));
////        mailSendPage.sendEmail(UserFactory.getProperties("testEmailText"), UserFactory.getProperties("emailAddress"));
//        mailSendPage.goToEmailPage();
////for default email checking
////        emailPage.checkEmail(UserFactory.getProperties("testEmailText"),mailSendPage.emailSubject);
////        emailPage.checkEmail(emailPage.emailBodyForCheck, mailSendPage.emailSubject);
//
//        mailSendPage.verifyIsOpen(MailSendPage.getMailCreateButtonForCheck());
//    }

    //    public static void checkEmailDeleting() {
//        EmailPage emailPage = new EmailPage();
//        String deleteEmailTime = emailPage.getDeleteEmailTime();
//        //type number email for deleting
//        emailPage.setEmailNumForDelete(4);
//        emailPage.deleteEmail();
//        String latestEmailTime = emailPage.getLatestEmailTime();
//        emailPage.checkIfEmailIsDeleted(latestEmailTime, deleteEmailTime);
//    }
    public static void checkEmailDeleting() {
        EmailPage emailPage = new EmailPage();
        //type number email for deleting
        emailPage.setEmailSubjectForDelete("Subject xOHQaTqqoWnn");
        emailPage.deleteEmailBySubject();
        new EmailSendPageBO().checkIfEmailIsDeletedBySubject(String.valueOf(new MailSendPage().emailSubject));
    }

    //todo винести в  ВО
    public static void filesComparing(String pathToFile1, String pathToFile2) {
        BasePage.pauseSec(2);
        Path path1 = MailSendPage.getPathToFile(pathToFile1);
        Path path2 = MailSendPage.getPathToFile(pathToFile2);
        try (BufferedInputStream fis1 = new BufferedInputStream(new FileInputStream(path1.toFile()));
             BufferedInputStream fis2 = new BufferedInputStream(new FileInputStream(path2.toFile()))) {
            if (fis1.equals(fis2)) {
                LOGGER.warn("The files are different");
                Assert.fail("The files are different");
            } else {
                LOGGER.info("Files are the same");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    public static void checkSortingEmailsOnEmailPage() {
        new EmailSendPageBO().checkEmailOrder();
    }

    //todo винести в  ВО
    @Step("check If Email Is Deleted By Subject")
    public void checkIfEmailIsDeletedBySubject(String subjectForDeleting) {
        action.moveToElement(mailSendPage.getWebElementByXpath("/html/body"));
        List<WebElement> subjectList = DriverFactory.getWebDriver().findElements(By.xpath(new MailSendPage().getSubjectOnEmailPageXpath()));
        List<String> subjectTextList = new ArrayList<>();
        mailSendPage
                .waitForElement(new MailSendPage().getEmailDataElement(), 10);

        for (WebElement element : subjectList) {
            if (element.getText().equals(new MailSendPage().getEmailSubjectForDeleteText())) {
                Assert.fail("Email deleting by SUBJECT failed!!!! \n email didn't DELETED or the email with the similar title is present");
            }
        }
    }


}
