package org.Eleks.Gmail.data_provider;

import org.Eleks.Gmail.models.User;
import org.testng.annotations.DataProvider;

public class CustomDataProvider {
//    for paralel run
//    @DataProvider(name = "false_Credentials",parallel = true)
    @DataProvider(name = "false_Credentials")
    public Object[][] falseCredentials(){
        User user = new User();
user.setUserName("asdasdfalseUserName1");
user.setPassword("falsePassw0rdName1");
        Object[][] obj= {{user}};
        return obj;
    }
//    @DataProvider(name = "false_Credentials")
//    public Object[][] falseCredentials(){
//        Object[][] obj= {{"asdasdfalseUserName1", "falsePassw0rdName1"},
//                {"1", "1"}};
//
//        return obj;
//    }
}
