package org.e_commerce_db.view;

import org.e_commerce_db.utils.StringUtils;

import static org.e_commerce_db.utils.OutputUtil.println;

public class AuthPage {
    public void printAuthMenu() {
        println(StringUtils.AUTH_MENU);
    }

    public void printThankYou() {
        println(StringUtils.THANK_YOU);
    }
}
