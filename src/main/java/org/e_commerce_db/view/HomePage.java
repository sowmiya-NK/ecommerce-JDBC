package org.e_commerce_db.view;

import org.e_commerce_db.utils.StringUtils;

import static org.e_commerce_db.utils.OutputUtil.println;

public class HomePage {
    public void printMenu() {
        println(StringUtils.WELCOME_MESSAGE);
        println(StringUtils.HOME_MENU);
    }
}
