package org.e_commerce_db.controller;

import org.e_commerce_db.dao.CategoryDao;
import org.e_commerce_db.model.Category;
import org.e_commerce_db.utils.AppException;
import org.e_commerce_db.utils.StringUtils;
import org.e_commerce_db.view.CategoryPage;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.e_commerce_db.utils.AppInput.enterInt;
import static org.e_commerce_db.utils.OutputUtil.println;

public class CategoryController {
    private final CategoryPage categoryPage;
    private final ProductController productController;
    private final HomeController homeController;
    private final CategoryDao categoryDao;

    public CategoryController(HomeController homeController) {
        this.homeController=homeController;
        categoryPage=new CategoryPage();
        categoryDao=new CategoryDao();
        productController=new ProductController(homeController);
    }

    public void printMenu() {
        try {
            ArrayList<Category> categories = categoryDao.getCategories();
            categoryPage.printMenu(categories);
            int choice = enterInt(StringUtils.ENTER_CHOICE);

            if (choice == 99) {
                homeController.printMenu();
            } else {
                int validCategoryId = 0;
                for (Category category : categories) {
                    if (category.getId() == choice) {
                        validCategoryId = category.getId();
                        break;
                    }
                }


                if (validCategoryId != 0) {
                    productController.showProducts(validCategoryId);
                } else {
                    invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
                }
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void invalidChoice(AppException appException) {
        println(appException.getMessage());
        printMenu();
    }
}
