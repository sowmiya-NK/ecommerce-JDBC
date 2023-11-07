package org.e_commerce_db.view;

import org.e_commerce_db.model.Product;
import org.e_commerce_db.utils.StringUtils;

import java.util.ArrayList;

import static org.e_commerce_db.utils.OutputUtil.println;

public class ProductPage {
    public void printProducts(ArrayList<Product> products) {
        try {
            println("#---------------------#");
            println(StringUtils.PRODUCT_MENU);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Product product : products) {
            println(product.getId() + ". " + product.getTitle() + " - Rs." + product.getPrice());
        }
        println(StringUtils.BACK_OPTION);
    }

    public void printSuccess() {
        try {
            println("#---------------------#");
            println(StringUtils.CART_SUCCESS);
            println("#---------------------#");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
