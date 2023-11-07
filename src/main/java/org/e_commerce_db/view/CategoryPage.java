package org.e_commerce_db.view;

import org.e_commerce_db.model.Category;
import org.e_commerce_db.utils.StringUtils;

import java.util.ArrayList;

import static org.e_commerce_db.utils.OutputUtil.println;

public class CategoryPage {
    public void printMenu(ArrayList<Category> categories) {
        try {
            println("#---------------------#");
            println(StringUtils.CATEGORY_MENU);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Category category : categories) {
            println(category.getId() + ". " + category.getCategoryName());
        }
        println(StringUtils.BACK_OPTION);
    }
}
