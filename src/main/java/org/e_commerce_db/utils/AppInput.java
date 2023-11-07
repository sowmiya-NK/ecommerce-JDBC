package org.e_commerce_db.utils;

import static org.e_commerce_db.utils.AppScanner.getScanner;
import static org.e_commerce_db.utils.OutputUtil.print;

public class AppInput {
    public static String enterString(String msg) {
        print(msg);
        return getScanner().nextLine();
    }

    public static int enterInt(String msg) throws AppException {
        print(msg);
        int input;
        try {
            input = Integer.parseInt(getScanner().nextLine());
        } catch (Exception ex) {
            throw new AppException(StringUtils.INVALID_CHOICE);
        }
        return input;
    }
}
