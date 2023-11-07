package org.e_commerce_db.controller;

import org.e_commerce_db.dao.ProductDao;
import org.e_commerce_db.model.Product;
import org.e_commerce_db.utils.AppException;
import org.e_commerce_db.utils.StringUtils;
import org.e_commerce_db.view.ProductPage;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.e_commerce_db.utils.AppInput.enterInt;
import static org.e_commerce_db.utils.OutputUtil.println;

public class ProductController {
    private int categoryId = 0;
    private final ProductPage productsPage;
    private final CartController cartController;
    private final HomeController homeController;
    private final ProductDao productDao;

    public ProductController(HomeController homeController) {
        productsPage = new ProductPage();
        this.homeController = homeController;
        productDao=new ProductDao();
        cartController = new CartController(homeController);
    }
    public void showProducts(int categoryId) throws SQLException {
        this.categoryId = categoryId;
        ArrayList<Product> products = productDao.getProducts();
        if (categoryId != 0) {
            ArrayList<Product> categoryProducts = new ArrayList<>();
            for (Product product : products) {
                if (product.getCategory() == categoryId) {
                    categoryProducts.add(product);
                }
            }
            products = categoryProducts;
        }

        productsPage.printProducts(products);

        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            int validProductId = 0;

            if (choice == 99) {
                homeController.printMenu();
            } else {
                for (Product product : products) {
                    if (product.getId() == choice) {
                        validProductId = product.getId();
                        break;
                    }
                }

                if (validProductId != 0) {
                    cartController.addToCart(validProductId);
                    productsPage.printSuccess();
                    showProducts(categoryId);
                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }
    }

    private void invalidChoice(AppException appException) throws SQLException {
        println(appException.getMessage());
        showProducts(categoryId);
    }
}
