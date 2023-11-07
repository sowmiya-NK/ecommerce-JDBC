package org.e_commerce_db.view;

import org.e_commerce_db.utils.StringUtils;

import static org.e_commerce_db.utils.OutputUtil.println;

public class RegisterPage {
    public void printRegistrationSuccessful() {
        try {
            println("#---------------------#");
            println(StringUtils.REGISTRATION_SUCCESSFUL);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void passwordMisMatch() {
        try {
            println("#---------------------#");
            println(StringUtils.PASSWORD_MISMATCH);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
