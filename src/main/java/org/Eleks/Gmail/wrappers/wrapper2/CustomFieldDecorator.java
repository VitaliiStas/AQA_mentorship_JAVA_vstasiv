package org.Eleks.Gmail.wrappers.wrapper2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.List;

public class CustomFieldDecorator extends DefaultFieldDecorator {
    public CustomFieldDecorator(ElementLocatorFactory factory) {
        super(factory);
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        // this part is where we pre-filter the acceptable interfaces for our page objects
        if (!(WebElement.class.isAssignableFrom(field.getType())
                || String.class.isAssignableFrom(field.getType())
                || Element.class.isAssignableFrom(field.getType()) // this will be our example custom object
                || List.class.isAssignableFrom(field.getType())
                || isDecoratableList(field))) {
            return null;
        }

        ElementLocator locator = factory.createLocator(field);
        if (locator == null) {
            return null;
        }
        if (List.class.isAssignableFrom(field.getType())) {
            return proxyForListLocator(loader, locator);
        } else if (Element.class.isAssignableFrom(field.getType())) {
            return customProxyForLocator(loader, locator);
        } else if (WebElement.class.isAssignableFrom(field.getType())) {
            return proxyForLocator(loader, locator);
        } else {
            return null;
        }
    }

    protected Element customProxyForLocator(ClassLoader loader, ElementLocator locator) {
        return (Element) Proxy.newProxyInstance(
                loader, new Class[]{Element.class}, new ElementWrapperHandler(locator));
    }
}
