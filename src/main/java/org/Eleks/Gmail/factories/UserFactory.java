package org.Eleks.Gmail.factories;

import org.Eleks.Gmail.models.User;
import org.Eleks.Gmail.po.MailSendPage;
import org.Eleks.Gmail.utils.PropertyUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserFactory {
//todo розбити на два класи пуе проперті окремо(проперті ютіл) і гет юзер окремо

    public static User getUser() {
        User user = new User();
        user.setUserName(PropertyUtils.getProperties("userName2"));
        user.setPassword(PropertyUtils.getProperties("password2"));
        return user;
    }
}

