package org.Eleks.Gmail.wrappers.wrapper2;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

public class CustomElementLocatorFactory implements ElementLocatorFactory {

    private final SearchContext searchContext;

    public CustomElementLocatorFactory(SearchContext context) {
        this.searchContext = context;
    }

    @Override
    public ElementLocator createLocator(Field field) {
        FindBy annotation = field.getAnnotation(FindBy.class);
        return new DefaultElementLocator(this.searchContext, new Annotations(field));
    }
}