package org.e_commerce_db.view;

import org.e_commerce_db.utils.StringUtils;

import static org.e_commerce_db.utils.OutputUtil.println;


public class WelcomePage {
    public void welcome() {
        try {
            println(StringUtils.WELCOME_MESSAGE);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
