package org.Eleks.Gmail.wrappers.wrapper2;

import org.Eleks.Gmail.wrappers.wrapper1.ElementDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

/**
 * Element factory for wrapped elements. Similar to {@link PageFactory}
 */
public class ElementFactory2 {

public static <T> T initElements(WebDriver driver,T page) {
            PageFactory.initElements(
                new CustomFieldDecorator(
                        new DefaultElementLocatorFactory(driver)), page);
        return page;
   }
}
