package org.Eleks.Gmail.bo;

import org.Eleks.Gmail.factories.UserFactory;
import org.Eleks.Gmail.po.BasePage;
import org.Eleks.Gmail.po.EmailPage;
import org.Eleks.Gmail.po.MailSendPage;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class EmailSendPageBO {
    protected static List<String> sendToListOrCC = Arrays.asList(
            "tt8397519+1@gmail.com", "tt8397519+2@gmail.com", "tt8397519+3@gmail.com");


    public static void sendAndCheckEmail() {

        EmailPage emailPage = new EmailPage();
        MailSendPage mailSendPage = new MailSendPage();
        mailSendPage.setExpectedUrl(UserFactory.getProperties("expectedUrlMailSendPage"));
        mailSendPage.goToEmailPage();
        mailSendPage.verifyIsOpen(MailSendPage.getMailCreateButtonForCheck());
        mailSendPage.sendEmail(emailPage.generateRandomString(),
                sendToListOrCC, "src/main/resources/testImage.jpg");
        emailPage.downloadFile();
        MailSendPage.filesComparing(emailPage.getAbsolutePath("src/main/resources/testImage.jpg")
                ,"C:\\Users\\vitalii.stasiv\\Downloads\\testImage.jpg");
        emailPage.checkEmail(emailPage.emailBodyForCheck, mailSendPage.emailSubject, sendToListOrCC);
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


    public static void checkSortingEmailsOnEmailPage() {
        EmailPage emailPage = new EmailPage();
        emailPage.checkEmailOrder();
    }

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
        emailPage.setEmailSubjectForDelete("Subject JyvGLHyrbJqW");
        emailPage.deleteEmailBySubject();
        emailPage.checkIfEmailIsDeletedBySubject(String.valueOf(new MailSendPage().emailSubject));
    }
}
