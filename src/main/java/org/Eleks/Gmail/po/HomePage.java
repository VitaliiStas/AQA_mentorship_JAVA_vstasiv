package org.Eleks.Gmail.po;
import org.Eleks.Gmail.utils.PropertyUtils;


public class HomePage extends BasePage {

//todo винести статичні методи в BO
    public HomePage() {
        setExpectedUrl(PropertyUtils.getProperties("expectedUrlHomePage"));
    }

}
