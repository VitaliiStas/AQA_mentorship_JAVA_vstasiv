package org.Eleks.Gmail.po;
import org.Eleks.Gmail.utils.PropertyUtils;


public class HomePage extends BasePage {

    public HomePage() {
        setExpectedUrl(PropertyUtils.getProperties("expectedUrlHomePage"));
    }

}
