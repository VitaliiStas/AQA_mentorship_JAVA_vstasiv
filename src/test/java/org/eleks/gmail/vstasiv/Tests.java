package org.eleks.gmail.vstasiv;


import org.Eleks.Gmail.bo.LoginBO;
import org.Eleks.Gmail.bo.EmailSendPageBO;
import org.Eleks.Gmail.listeners.TestListener;
import org.Eleks.Gmail.po.EmailPage;
import org.Eleks.Gmail.po.HomePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class Tests extends BaseTest {

    @Test(priority = 4)
    public void singInTest() {
        LoginBO.login();
        Assert.fail();
    }

    @Test(priority = 1)
    public void singInFailedTest() {
        LoginBO.loginFailed();
    }

    @Test(priority = 2)
    public void goToMailPageTest() {
        LoginBO.login();
        HomePage.goToMailSendPage();
    }

    @Test(priority = 3)
    public void sendAndCheckEmailTest() {
        LoginBO.login();
        HomePage.goToMailSendPage();
        EmailSendPageBO.sendAndCheckEmail();
    }

    @Test(priority = 3)
    public void sortingEmailsOnEmailPageTest() {
        LoginBO.login();
        HomePage.goToMailSendPage();
        EmailSendPageBO.checkSortingEmailsOnEmailPage();
    }

    @Test(priority = 4)
    public void emailDeletingTest() {
        LoginBO.login();
        HomePage.goToMailSendPage();
        EmailSendPageBO.checkEmailDeleting();
    }
}
