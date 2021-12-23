package org.Eleks.Gmail.wrappers.wrapper2;
/*write all custom methods which should be used in wrapped class,
for working wrapping we should create element in the next way:
@FindBy(xpath = "//*[@id='identifierId']")
    private CustomWebElementWraper emailField2;
    CustomWebElementWraper interface with all methods
    custom + default
*/
public interface CustomWebElementWraper extends Element {
    void killAllHuman();

    void clickWrap();

    void submitWrap();

    void sendKeysWrap(CharSequence... keysToSend);

    void fillTheField(String text);

    void clickButton();
}
