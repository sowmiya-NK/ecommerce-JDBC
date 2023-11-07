package org.e_commerce_db.utils;

import org.e_commerce_db.model.Cart;
import org.e_commerce_db.model.User;

public class UserUtil {
    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        UserUtil.loggedInUser = loggedInUser;
    }

}
