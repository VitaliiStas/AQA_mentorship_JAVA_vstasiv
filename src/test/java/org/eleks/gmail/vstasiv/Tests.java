package org.eleks.gmail.vstasiv;


import org.Eleks.Gmail.bo.LoginBO;
import org.Eleks.Gmail.bo.EmailSendPageBO;
import org.Eleks.Gmail.data_provider.CustomDataProvider;
import org.Eleks.Gmail.listeners.TestListener;

import org.Eleks.Gmail.models.User;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners(TestListener.class)
public class Tests extends BaseTest {



    @Test
    public void singInTest() {
        LoginBO.login();
    }

    @Test(dataProvider = "false_Credentials",dataProviderClass = CustomDataProvider.class)
    public void singInFailedTest(User user) {
        LoginBO.loginFailed(user.getUserName(), user.getPassword());
    }
//    @Test(dataProvider = "false_Credentials",dataProviderClass = CustomDataProvider.class)
//    public void singInFailedTest(String email,String password) {
//        LoginBO.loginFailed(email,password);
//    }


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
                .createFullEmail()
                .sendAndCheckEmail();
    }

    @Test
    public void sendAndCheckEmailWithBuilderTest() {
        LoginBO
                .login()
                .goToMailSendPage()
                .createFullEmail()
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
                .createFullEmail()
                .checkEmailDeleting();
    }

    @Test
    public void emailDeletingTestWithSubject() {
        LoginBO
                .login()
                .goToMailSendPage()
                .createFullEmail()
                .checkEmailDeletingWithSubject();

    }

    @Test
    public void sendEmailApiTest() {
        EmailSendPageBO.createFullEmail().sendAndCheckEmailApi();
    }

    @Test
    public void sendEmptyEmailText(){
        LoginBO
                .login()
                .goToMailSendPage()
                .createEmptyEmail()
                .sendAndCheckEmptyEmail();
    }
    @Test
    public void send1000SizeEmailText(){
        LoginBO
                .login()
                .goToMailSendPage()
                .create1000EmailText().sendAndCheck1000SizeEmail();
    }


}
