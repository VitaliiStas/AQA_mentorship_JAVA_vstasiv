package org.Eleks.Gmail.wrappers.wrapper2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SelectElementWrapperHandler implements InvocationHandler {
    private final ElementLocator locator;

    public SelectElementWrapperHandler(ElementLocator locator) {
        this.locator = locator;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        WebElement element;
        try {
            element = locator.findElement();
        } catch (Exception e) {
            if ("toString".equals(method.getName())) {
                return "Proxy select(element) for: " + locator.toString();
            }
            else throw e;
        }
        ElementImpl el = new ElementImpl(element);

        try {
            return method.invoke(el, args);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
