package org.Eleks.Gmail.bo;

import io.qameta.allure.Step;
import org.Eleks.Gmail.po.HomePage;

public class HomePageBO {
    //todo return EmailSendPageBO
    @Step("go to email send page")
    public static EmailSendPageBO goToMailSendPage() {
//      clickOnElement(menu, "aria-expanded", "true"); //menu is opened by default
        HomePage homePage = new HomePage();
        homePage.goToServicesMenu();
        homePage.clickOnMailIcon();
        homePage.switchToTab(1);
     return new EmailSendPageBO();
    }

}
