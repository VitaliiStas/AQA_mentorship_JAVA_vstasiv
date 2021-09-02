package org.eleks.gmail.vstasiv;


import org.Eleks.Gmail.bo.LoginBO;
import org.Eleks.Gmail.bo.EmailSendPageBO;
import org.Eleks.Gmail.listeners.TestListener;
import org.Eleks.Gmail.po.HomePage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class Tests extends BaseTest {

    @Test(priority = 1)
    public void singIn() {
        LoginBO.login();
    }

    @Test(priority = 2)
    public void goToMailPage() {
        LoginBO.login();
        HomePage.goToMailSendPage();
    }

    @Test(priority = 3)
    public void sendAndCheckEmailTest() {
        LoginBO.login();
        HomePage.goToMailSendPage();
        EmailSendPageBO.sendAndCheckEmail();

    }
}
