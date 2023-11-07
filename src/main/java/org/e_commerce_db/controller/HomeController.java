package org.e_commerce_db.controller;

import org.e_commerce_db.utils.AppException;
import org.e_commerce_db.utils.StringUtils;
import org.e_commerce_db.view.HomePage;

import java.sql.SQLException;

import static org.e_commerce_db.utils.AppInput.enterInt;
import static org.e_commerce_db.utils.OutputUtil.println;
import static org.e_commerce_db.utils.UserUtil.setLoggedInUser;

public class HomeController {
    private final HomePage homePage;
    private final AuthController authController;
    private final CategoryController categoryController;
    private final ProductController productController;
    private final CartController cartController;
    private final OrderController orderController;


    public HomeController(AuthController authController) {
        homePage = new HomePage();
        categoryController=new CategoryController(this);
        productController=new ProductController(this);
        cartController=new CartController(this);
        this.authController=authController;
        orderController=new OrderController(this);
    }

    public void printMenu() {
        homePage.printMenu();
        try {
            int choice = enterInt(StringUtils.ENTER_CHOICE);
            if (choice == 1) {
                categoryController.printMenu();
            }
            else if (choice == 2) {
                productController.showProducts(0);
            }
            else if (choice == 3) {
                cartController.printCart();
            }
            else if (choice == 4) {
                orderController.printOrders();
            }
            else if (choice == 5) {
                setLoggedInUser(null);
                authController.authMenu();
            }
      else {
                invalidChoice(new AppException(StringUtils.INVALID_CHOICE));
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
