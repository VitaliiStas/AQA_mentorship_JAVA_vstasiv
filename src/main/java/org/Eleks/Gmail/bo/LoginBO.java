package org.Eleks.Gmail.bo;

import org.Eleks.Gmail.factories.UserFactory;
import org.Eleks.Gmail.po.BasePage;
import org.Eleks.Gmail.po.HomePage;
import org.Eleks.Gmail.po.LoginPage;
import org.Eleks.Gmail.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginBO {
    private static final Logger LOGGER = LogManager.getLogger(LoginBO.class);

    //Sing in  with correct credentials
    public static void login() {
        User user = UserFactory.getUser();
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.login(user.getUserName(), user.getPassword());
        homePage.checkIfProfileImageIsPresent();
        homePage.verifyIsOpen();
        LOGGER.info("Login successfully");
    }

    //Sing in  with incorrect credentials
    public static void loginFailed() {
        User user = UserFactory.getUser();
        LoginPage loginPage = new LoginPage();
        HomePage homePage = loginPage.loginFailed(user.getUserName(), user.getPassword());
    }
}
