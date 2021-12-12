package org.Eleks.Gmail.po;

import io.qameta.allure.Step;
import org.Eleks.Gmail.bo.EmailSendPageBO;
import org.Eleks.Gmail.factories.UserFactory;


public class HomePage extends BasePage {

//todo винести статичні методи в BO
    public HomePage() {
        setExpectedUrl(UserFactory.getProperties("expectedUrlHomePage"));
    }

}
