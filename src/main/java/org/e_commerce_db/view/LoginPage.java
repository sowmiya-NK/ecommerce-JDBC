package org.e_commerce_db.view;

import org.e_commerce_db.utils.StringUtils;

import static org.e_commerce_db.utils.OutputUtil.println;

public class LoginPage {
    public void printInvalidCredentials() {
        try {
            println("#---------------------#");
            println(StringUtils.INVALID_CREDENTIALS);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
