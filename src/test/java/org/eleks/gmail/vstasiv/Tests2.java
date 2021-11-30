package org.eleks.gmail.vstasiv;

import org.Eleks.Gmail.bo.EmailSendPageBO;
import org.Eleks.Gmail.bo.LoginBO;
import org.Eleks.Gmail.listeners.Retry;
import org.Eleks.Gmail.listeners.TestListener;
import org.Eleks.Gmail.po.HomePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


public class Tests2 extends BaseTest {

    @Test
    public void singInTest() {
        LoginBO.login();
        Assert.fail();
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
    public void sendEmailApiTest() {
        EmailSendPageBO.create().sendAndCheckEmailApi();
    }

}


