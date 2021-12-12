package org.eleks.gmail.vstasiv;


import org.Eleks.Gmail.bo.LoginBO;
import org.Eleks.Gmail.bo.EmailSendPageBO;
import org.Eleks.Gmail.listeners.TestListener;
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
        LoginBO
                .login()
                .goToMailSendPage();
    }

    @Test
    public void sendAndCheckEmailTest() {
        LoginBO
                .login()
                .goToMailSendPage()
                .create()
                .sendAndCheckEmail();
    }

    @Test
    public void sendAndCheckEmailWithBuilderTest() {
        LoginBO
                .login()
                .goToMailSendPage()
                .create()
                .sendAndCheckEmailWithBuilder();
    }

    @Test
    public void sortingEmailsOnEmailPageTest() {
        LoginBO
                .login()
                .goToMailSendPage()
                .checkSortingEmailsOnEmailPage();
    }

    @Test
    public void emailDeletingTest() {
        LoginBO
                .login()
                .goToMailSendPage()
                .create()
                .checkEmailDeleting();
    }

    @Test
    public void emailDeletingTestWithSubject() {
        LoginBO
                .login()
                .goToMailSendPage()
                .create()
                .checkEmailDeletingWithSubject();

    }

    @Test
    public void sendEmailApiTest() {
        EmailSendPageBO.create().sendAndCheckEmailApi();
    }

}
