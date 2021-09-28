package org.Eleks.Gmail.bo;

import org.Eleks.Gmail.factories.UserFactory;
import org.Eleks.Gmail.po.BasePage;
import org.Eleks.Gmail.po.EmailPage;
import org.Eleks.Gmail.po.MailSendPage;
import org.openqa.selenium.By;

public class EmailSendPageBO {
    public static void sendAndCheckEmail() {

//        MailSendPage mailSendPage = new MailSendPage();
        EmailPage emailPage = new EmailPage();
        MailSendPage mailSendPage = new MailSendPage();
        emailPage.setExpectedUrl(UserFactory.getProperties("expectedUrlMailSendPage"));
        emailPage.sendEmail(UserFactory.getProperties("testEmailText"), UserFactory.getProperties("emailAddress"));
        emailPage.goToEmailPage();
//for default email checking
//        emailPage.checkEmail(UserFactory.getProperties("testEmailText"),mailSendPage.emailSubject);
        emailPage.checkEmail(emailPage.emailBodyForCheck, mailSendPage.emailSubject);

        emailPage.verifyIsOpen(MailSendPage.getMailCreateButtonForCheck());
    }

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
        emailPage.setEmailSubjectForDelete("deleteMe");
        emailPage.deleteEmailBySubject();
        emailPage.checkIfEmailIsDeletedBySubject("deleteMe");
    }
}
