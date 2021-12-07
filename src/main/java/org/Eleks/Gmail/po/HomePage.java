package org.Eleks.Gmail.po;

import io.qameta.allure.Step;
import org.Eleks.Gmail.factories.UserFactory;


public class HomePage extends BasePage {


    public HomePage() {
        setExpectedUrl(UserFactory.getProperties("expectedUrlHomePage"));
    }

    @Step("go to email send page")
    public static void goToMailSendPage() {
//      clickOnElement(menu, "aria-expanded", "true"); //menu is opened by default
        HomePage homePage = new HomePage();
        homePage.goToServicesMenu();
        homePage.clickOnMailIcon();
        homePage.switchToTab(1);
        new MailSendPage();
    }
}
