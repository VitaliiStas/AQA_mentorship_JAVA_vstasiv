package org.eleks.gmail.vstasiv;


import org.Eleks.Gmail.bo.LoginBO;
import org.Eleks.Gmail.bo.EmailSendPageBO;
import org.Eleks.Gmail.data_provider.CustomDataProvider;
import org.Eleks.Gmail.listeners.TestListener;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners(TestListener.class)
public class Tests extends BaseTest {



    @Test
    public void singInTest() {
        LoginBO.login();
    }

    @Test(dataProvider = "false_Credentials",dataProviderClass = CustomDataProvider.class)
    public void singInFailedTest(String email,String password) {
        LoginBO.loginFailed(email,password);
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
                .create_Full_Email()
                .sendAndCheckEmail();
    }

    @Test
    public void sendAndCheckEmailWithBuilderTest() {
        LoginBO
                .login()
                .goToMailSendPage()
                .create_Full_Email()
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
                .create_Full_Email()
                .checkEmailDeleting();
    }

    @Test
    public void emailDeletingTestWithSubject() {
        LoginBO
                .login()
                .goToMailSendPage()
                .create_Full_Email()
                .checkEmailDeletingWithSubject();

    }

    @Test
    public void sendEmailApiTest() {
        EmailSendPageBO.create_Full_Email().sendAndCheckEmailApi();
    }

    @Test
    public void sendEmptyEmailText(){
        LoginBO
                .login()
                .goToMailSendPage()
                .create_Empty_Email()
                .sendAndCheckEmptyEmail();
    }
    @Test
    public void send1000SizeEmailText(){
        LoginBO
                .login()
                .goToMailSendPage()
                .create_1000_Email_Text().sendAndCheck1000SizeEmail();
    }


}
