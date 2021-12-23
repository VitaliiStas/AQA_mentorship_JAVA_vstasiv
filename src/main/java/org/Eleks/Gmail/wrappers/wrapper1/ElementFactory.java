package org.Eleks.Gmail.wrappers.wrapper1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

/**
 * Element factory for wrapped elements. Similar to {@link org.openqa.selenium.support.PageFactory}
 */
public class ElementFactory  {

public static <T> T initElements(WebDriver driver,T page) {
            PageFactory.initElements(
                new ElementDecorator(
                        new DefaultElementLocatorFactory(driver)), page);
        return page;
   }
}
