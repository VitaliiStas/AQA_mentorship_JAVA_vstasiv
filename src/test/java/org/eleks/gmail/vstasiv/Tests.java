package org.eleks.gmail.vstasiv;


import org.Eleks.Gmail.bo.LoginBO;
import org.Eleks.Gmail.bo.EmailSendPageBO;
import org.Eleks.Gmail.listeners.TestListener;
import org.Eleks.Gmail.po.HomePage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class Tests extends BaseTest {

    @Test
    public void singInTest() {
        LoginBO.login();
    }

    @Test
    public void singInFailedTest() {
        LoginBO.loginFailed();
    }

    @Test
    public void goToMailPageTest() {
        LoginBO.login();
        HomePage.goToMailSendPage();
    }

    @Test
    public void sendAndCheckEmailTest() {
        LoginBO.login();
        HomePage.goToMailSendPage();
        EmailSendPageBO.create().sendAndCheckEmail();
    }

    @Test
    public void sendAndCheckEmailWithBuilderTest() {
        LoginBO.login();
        HomePage.goToMailSendPage();
        EmailSendPageBO.create().sendAndCheckEmailWithBuilder();
    }

    @Test
    public void sortingEmailsOnEmailPageTest() {
        LoginBO.login();
        HomePage.goToMailSendPage();
        EmailSendPageBO.checkSortingEmailsOnEmailPage();
    }

    @Test
    public void emailDeletingTest() {
        LoginBO.login();
        HomePage.goToMailSendPage();
        EmailSendPageBO.checkEmailDeleting();
    }

    @Test
    public void emailDeletingTestWithSubject() {
        LoginBO.login();
        HomePage.goToMailSendPage();
        EmailSendPageBO.create().checkEmailDeletingWithSubject();
    }

    @Test
    public void sendEmailApiTest() {
        EmailSendPageBO.create().sendAndCheckEmailApi();
    }

}
