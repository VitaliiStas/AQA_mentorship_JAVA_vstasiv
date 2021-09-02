package org.Eleks.Gmail.bo;

import org.Eleks.Gmail.factories.UserFactory;
import org.Eleks.Gmail.po.BasePage;
import org.Eleks.Gmail.po.EmailPage;
import org.Eleks.Gmail.po.MailSendPage;

public class EmailSendPageBO {
    public static void sendAndCheckEmail() {

//        MailSendPage mailSendPage = new MailSendPage();
        EmailPage emailPage = new EmailPage();
        emailPage.setExpectedUrl(UserFactory.getProperties("expectedUrlMailSendPage"));
        emailPage.sendEmail(UserFactory.getProperties("testEmailText"), UserFactory.getProperties("emailAddress"));
        emailPage.goToEmailPage();
        emailPage.checkEmail(UserFactory.getProperties("testEmailText"));

        emailPage.verifyIsOpen(MailSendPage.getMailCreateButtonForCheck());
    }
}
