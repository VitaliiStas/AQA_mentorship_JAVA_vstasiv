package org.Eleks.Gmail.data_provider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {
    @DataProvider(name = "false_Credentials")
    public Object[][] falseCredentials(){
        Object[][] obj= {{"asdasdfalseUserName", "falsePassw0rdName"}};
        return obj;
    }
}
