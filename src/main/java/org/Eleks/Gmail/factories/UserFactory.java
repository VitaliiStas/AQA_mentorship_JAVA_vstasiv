package org.Eleks.Gmail.factories;

import org.Eleks.Gmail.models.User;
import org.Eleks.Gmail.utils.PropertyUtils;

public class UserFactory {

    public static User getUser() {
        User user = new User();
        user.setUserName(PropertyUtils.getProperties("userName2"));
        user.setPassword(PropertyUtils.getProperties("password2"));
        return user;
    }
}

