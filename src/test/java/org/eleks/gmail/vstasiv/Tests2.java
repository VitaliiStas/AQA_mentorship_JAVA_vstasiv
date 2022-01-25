package org.eleks.gmail.vstasiv;

import org.Eleks.Gmail.bo.EmailSendPageBO;
import org.Eleks.Gmail.bo.LoginBO;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Tests2 extends BaseTest {

    @Test
    public void singInTest() {
        LoginBO.login();
    }

    @Test
    public void singInFailedTest() {
        Assert.fail();
        LoginBO.loginFailed();

    }

    @Test
    public void goToMailPageTest() {
        LoginBO.login().goToMailSendPage();;
        Assert.fail();

    }

    @Test
    public void sendEmailApiTest() {
        EmailSendPageBO.create().sendAndCheckEmailApi();
    }

}


